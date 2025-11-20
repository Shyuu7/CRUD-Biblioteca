package com.br.infnet.service;

import com.br.infnet.model.Livro;
import com.br.infnet.security.SecurityConfig;
import com.br.infnet.repository.interfaces.iLivroRepository;
import java.util.*;

public class LivroService {
    private final iLivroRepository livroRepository;

    public LivroService(iLivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void cadastrarLivroNoAcervo(Livro livro) {
        validarLivro(livro);

        if (livroRepository.existeISBN(livro.getIsbn())) {
            throw new IllegalArgumentException("Já existe um livro cadastrado com este ISBN");
        }

        livroRepository.salvarLivro(livro);
    }

    public Livro buscarLivroPorIDNoAcervo(int id) {
        Livro livro = livroRepository.buscarLivroPorId(id);
        if (livro == null) {
            throw new NoSuchElementException("Livro não encontrado");
        }
        return livro;
    }

    public List<Livro> buscarLivroPorTituloNoAcervo(String titulo) {
        validarTermoBusca(titulo, "Título");
        return livroRepository.listarLivrosPorTitulo(titulo);
    }

    public Livro buscarLivroPorISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }
        String isbnBusca = SecurityConfig.processarEntrada(isbn);
        return livroRepository.buscarLivroPorISBN(isbnBusca);
    }

    public List<Livro> buscarLivroPorAutorNoAcervo(String autor) {
        validarTermoBusca(autor, "Autor");
        String autorBusca = SecurityConfig.processarEntrada(autor.trim().toLowerCase());
        return livroRepository.listarLivrosPorAutor(autorBusca);
    }

    public void atualizarLivroDoAcervo(int id, String titulo, String autor, String isbn) {
        Livro livro = livroRepository.buscarLivroPorId(id);

        if (livro == null) {
            throw new NoSuchElementException("Livro não encontrado");
        }
        String tituloProcessado = SecurityConfig.processarEntrada(titulo);
        String autorProcessado = SecurityConfig.processarEntrada(autor);
        String isbnProcessado = SecurityConfig.processarEntrada(isbn);

        buscarLivroPorISBN(isbnProcessado);
        if (livroRepository.existeISBN(isbnProcessado)) {
            throw new IllegalArgumentException("Já existe um livro cadastrado com este ISBN");
        }

        livro.setTitulo(tituloProcessado);
        livro.setAutor(autorProcessado);
        livro.setIsbn(isbnProcessado);
        livroRepository.atualizarLivro(livro);
    }

    public void removerLivroDoAcervo(int id) {
        Livro livro = livroRepository.buscarLivroPorId(id);

        if (livro == null) {
            throw new NoSuchElementException("Livro não encontrado");
        }

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro está emprestado e não pode ser removido do acervo");
        }
        livroRepository.removerLivro(id);
    }

    public List<Livro> listarLivrosDoAcervo() {
        return livroRepository.listarLivros();
    }

    private void validarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }

        SecurityConfig.validarTitulo(livro.getTitulo());
        SecurityConfig.validarAutor(livro.getAutor());
        SecurityConfig.validarIsbn(livro.getIsbn());
    }

    private void validarTermoBusca(String termo, String campo) {
        if (termo == null || termo.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " inválido");
        }
    }
}
