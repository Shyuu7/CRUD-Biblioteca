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
    public Livro buscarLivroPorId(int id) {
        return livroService.buscarLivroPorIDNoAcervo(id);
    }

    @Override
    public Livro buscarLivroPorISBN(String isbn) {
        return livroService.buscarLivroPorISBN(isbn);
    }

    @Override
    public List<Livro> listarLivros() {
        return livroService.listarLivrosDoAcervo();
    }

    @Override
    public List<Livro> listarLivrosPorTitulo(String titulo) {
        return livroService.buscarLivroPorTituloNoAcervo(titulo);
    }

    @Override
    public List<Livro> listarLivrosPorAutor(String autor) {
        return livroService.buscarLivroPorAutorNoAcervo(autor);
    }

    @Override
    public boolean existeISBN(String isbn) {
        return livroService.existeISBN(isbn);
    }

    @Override
    public void salvarLivro(Livro livro) {
        livroService.cadastrarLivroNoAcervo(livro);
    }

    @Override
    public void atualizarLivro(int id, String titulo, String autor, String isbn) {
        livroService.atualizarLivroDoAcervo(id, titulo, autor, isbn);
    }

    @Override
    public void removerLivro(int id) {
        livroService.removerLivroDoAcervo(id);
    }
}
