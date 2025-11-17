package com.br.infnet.repository.interfaces;
import com.br.infnet.model.Livro;
import java.util.List;

public interface iLivroRepository {
    Livro buscarLivroPorId(int id);
    Livro buscarLivroPorISBN(String isbn);
    List<Livro> listarLivros();
    List<Livro> listarLivrosPorTitulo(String titulo);
    List<Livro> listarLivrosPorAutor(String autor);
    boolean existeISBN(String isbn);
    void salvarLivro(Livro livro);
    void atualizarLivro(int id, String titulo, String autor, String isbn);
    void removerLivro(int id);
 }

