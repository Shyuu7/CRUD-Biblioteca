package com.br.infnet.service;

import com.br.infnet.model.Emprestimo;
import com.br.infnet.model.Livro;

import java.util.Map;

public class EmprestimoService {
    private final Map<Integer, Emprestimo> emprestimos;
    private final Map<Integer, Livro> livros;
    private int proximoId;

    public EmprestimoService(Map<Integer, Livro> livros) {
        this.emprestimos = new java.util.HashMap<>();
        this.livros = livros;
        this.proximoId = 1;
    }

}
