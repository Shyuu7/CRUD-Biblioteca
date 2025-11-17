package com.br.infnet.repository;

import com.br.infnet.model.Emprestimo;

import java.util.List;

public interface iEmprestimoRepository {
    void salvarEmprestimo(Emprestimo emprestimo);
    void removerEmprestimo(int id);
    List<Emprestimo> listarEmprestimosAtivos();
    Emprestimo buscarPorLivroId(int livroId);
}
