package com.br.infnet.controller;

import com.br.infnet.model.Livro;
import com.br.infnet.service.EmprestimoService;
import com.br.infnet.utils.FormValidator;
import com.br.infnet.utils.ErrorHandler;
import com.br.infnet.service.MultaPendenteException;
import com.br.infnet.view.EmprestimoView;
import com.br.infnet.view.LivroView;
import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

public class EmprestimoController {
    private Map<Integer, Livro> livros = new HashMap<>();
    private final EmprestimoService service = new EmprestimoService(livros);

    public EmprestimoController(Javalin app) {
        app.get("/", ctx -> ctx.redirect("/emprestimos"));

        //********************Rotas para empréstimos**************************
        app.get("/emprestimos", ctx -> {
            try {
                ctx.html(EmprestimoView.renderEmprestimos(service.listarEmprestimos(), service));
            } catch (Exception e) {
                ctx.html(ErrorHandler.handleDatabaseError());
            }
        });

        app.get("/livros/{id}/emprestar", ctx -> {
            try {
                Integer idParam = ctx.pathParamAsClass("id", Integer.class).getOrDefault(null);
                if (idParam == null) {
                    ctx.html(ErrorHandler.handleValidationError("ID inválido"));
                    return;
                }

                Livro livro = service.obterLivroPorId(idParam);
                if (livro == null) {
                    ctx.html(ErrorHandler.handleNotFound("livro"));
                    return;
                }

                if (!livro.isDisponivel()) {
                    ctx.html(ErrorHandler.handleBusinessLogicError("Livro não está disponível para empréstimo"));
                    return;
                }

                ctx.html(LivroView.renderFormEmprestimo(livro));

            } catch (Exception e) {
                ctx.html(ErrorHandler.handleError(e));
            }
        });

        app.post("/livros/{id}/emprestar", ctx -> {
            try {
                Integer idParam = ctx.pathParamAsClass("id", Integer.class).getOrDefault(null);
                if (idParam == null) {
                    ctx.html(ErrorHandler.handleValidationError("ID inválido"));
                    return;
                }

                String prazoStr = ctx.formParam("prazo");

                // Validar prazo
                FormValidator.ValidationResult validation = FormValidator.validatePrazo(prazoStr);
                if (!validation.isValid()) {
                    Livro livro = service.obterLivroPorId(idParam);
                    if (livro == null) {
                        ctx.html(ErrorHandler.handleNotFound("livro"));
                        return;
                    }

                    Map<String, Object> model = new HashMap<>();
                    model.put("livro", livro);
                    model.put("erro", validation.getErrorMessage());
                    ctx.html(LivroView.renderFormEmprestimo(livro, validation.getErrorMessage()));
                    return;
                }

                // Verificar se livro ainda existe e está disponível
                Livro livro = service.obterLivroPorId(idParam);
                if (livro == null) {
                    ctx.html(ErrorHandler.handleNotFound("livro"));
                    return;
                }

                if (!livro.isDisponivel()) {
                    ctx.html(ErrorHandler.handleBusinessLogicError("Livro não está mais disponível para empréstimo"));
                    return;
                }

                int prazo = Integer.parseInt(prazoStr.trim());
                service.emprestarLivro(idParam, prazo);
                ctx.redirect("/emprestimos");

            } catch (Exception e) {
                ctx.html(ErrorHandler.handleError(e));
            }
        });

        app.post("/livros/{id}/devolver", ctx -> {
            try {
                Integer idParam = ctx.pathParamAsClass("id", Integer.class).getOrDefault(null);
                if (idParam == null) {
                    ctx.html(ErrorHandler.handleValidationError("ID inválido"));
                    return;
                }

                service.devolverLivro(idParam);
                ctx.redirect("/emprestimos");

            } catch (MultaPendenteException e) {
                ctx.html(LivroView.renderMultaPendente(e.getMessage()));
            } catch (Exception e) {
                ctx.html(ErrorHandler.handleError(e));
            }
        });
    }
}