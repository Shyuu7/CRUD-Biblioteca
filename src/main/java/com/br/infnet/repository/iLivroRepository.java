package com.br.infnet.repository;

import com.br.infnet.model.Livro;

import java.time.LocalDate;

public interface iLivroRepository {
    Livro obterLivroPorId(int id);
    void atualizarDisponibilidade(int id, boolean disponivel);
    void atualizarDadosEmprestimo(int id, LocalDate dataEmprestimo, int prazo, LocalDate dataEstimadaDevolucao);
    void excluirDadosEmprestimo(int id);
}
