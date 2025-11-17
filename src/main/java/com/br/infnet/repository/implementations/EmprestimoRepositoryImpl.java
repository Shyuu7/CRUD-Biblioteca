package com.br.infnet.repository.implementations;

import com.br.infnet.model.Emprestimo;
import com.br.infnet.model.Livro;
import com.br.infnet.repository.interfaces.iEmprestimoRepository;
import com.br.infnet.service.EmprestimoService;
import java.util.*;

public class EmprestimoRepositoryImpl implements iEmprestimoRepository {
    private final EmprestimoService emprestimoService;

    public EmprestimoRepositoryImpl(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Override
    public Livro buscarLivroPorId(int livroId) {
        return emprestimoService.obterLivroPorId(livroId);
    }

    @Override
    public void realizarEmprestimo(Emprestimo emprestimo) {
        Livro livro = buscarLivroPorId(emprestimo.getLivroId());
        emprestimoService.emprestarLivro(livro.getId(), emprestimo.getPrazoDevolucao());
    }

    @Override
    public void removerEmprestimo(Emprestimo emprestimo) {
        Livro livro = buscarLivroPorId(emprestimo.getLivroId());
        emprestimoService.devolverLivro(livro.getId());
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.listarEmprestimos();
    }
}
