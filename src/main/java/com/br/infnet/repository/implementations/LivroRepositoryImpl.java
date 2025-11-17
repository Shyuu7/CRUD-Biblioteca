package com.br.infnet.repository.implementations;

import com.br.infnet.model.Livro;
import com.br.infnet.repository.interfaces.iLivroRepository;
import com.br.infnet.service.LivroService;

import java.util.List;

public class LivroRepositoryImpl implements iLivroRepository {
    private final LivroService livroService;

    public LivroRepositoryImpl(LivroService livroService) {
        this.livroService = livroService;
    }

    @Override
    public Livro obterLivroPorId(int id) {
        return livroService.buscarLivroPorIDNoAcervo(id);
    }

    @Override
    public List<Livro> listarLivrosPorTitulo(String titulo) {
        return List.of();
    }

    @Override
    public List<Livro> listarLivrosPorAutor(String autor) {
        return List.of();
    }

    @Override
    public boolean existeISBN(String isbn) {
        return livroService.existeISBN(isbn);
    }

    @Override
    public void salvar(Livro livro) {
        livroService.cadastrarLivroNoAcervo(livro);
    }

    @Override
    public void atualizar(int id, String titulo, String autor, String isbn) {
        livroService.atualizarLivroDoAcervo(id, titulo, autor, isbn);
    }

    @Override
    public void remover(int id) {
        livroService.removerLivroDoAcervo(id);
    }
}
