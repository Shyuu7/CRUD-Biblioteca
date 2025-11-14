package com.br.infnet.service;

import com.br.infnet.model.Emprestimo;
import com.br.infnet.model.Livro;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EmprestimoService {
    private final Map<Integer, Emprestimo> emprestimos;
    private final Map<Integer, Livro> livros;
    private int proximoId;

    public EmprestimoService(Map<Integer, Livro> livros) {
        this.emprestimos = new HashMap<>();
        this.livros = livros;
        this.proximoId = 1;
    }

    public void emprestarLivro(int id, int prazoDevolucao) {
        validarPrazoEmprestimo(prazoDevolucao);

        Livro livro = livros.get(id);
        if (livro == null) {
            throw new NoSuchElementException("Livro não encontrado");
        }

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro já está emprestado");
        }

        Emprestimo emprestimo = new Emprestimo(proximoId++, LocalDate.now(),
                LocalDate.now().plusDays(prazoDevolucao), prazoDevolucao, 0);
        emprestimos.put(emprestimo.getId(), emprestimo);

        livro.setDataEmprestimo(LocalDate.now());
        livro.setPrazoDevolucao(prazoDevolucao);
        livro.setDataEstimadaDevolucao(livro.getDataEmprestimo().plusDays(prazoDevolucao));
        livro.setDisponivel(false);
    }

    private void validarPrazoEmprestimo(int prazo) {
        if (prazo <= 0) {
            throw new IllegalArgumentException("Prazo de devolução deve ser positivo");
        }
        if (prazo > 365) {
            throw new IllegalArgumentException("Prazo de devolução não pode exceder 365 dias");
        }
    }

    public void devolverLivro(int id) throws MultaPendenteException {
        Livro livro = livros.get(id);

        if (livro == null) {
            throw new NoSuchElementException("Livro não encontrado");
        }

        if (livro.isDisponivel()) {
            throw new IllegalStateException("Livro não está emprestado");
        }

        double multa = calcularMulta(id);

        if (multa > 0) {
            livro.setMulta(multa);
            throw new MultaPendenteException("Pendente pagamento de multa no valor de R$ " + String.format("%.2f", multa));
        }

        livro.setDataEfetivaDevolucao(LocalDate.now());
        livro.setMulta(0);
        livro.setDisponivel(true);
        livro.setPrazoDevolucao(0);
        livro.setDataEstimadaDevolucao(null);
        livro.setDataEfetivaDevolucao(null);
    }

}
