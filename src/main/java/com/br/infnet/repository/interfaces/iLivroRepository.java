package com.br.infnet.repository.interfaces;
import com.br.infnet.model.Livro;
import java.util.List;

public interface iLivroRepository {
    Livro obterLivroPorId(int id);
    List<Livro> listarLivrosPorTitulo(String titulo);
    List<Livro> listarLivrosPorAutor(String autor);
    boolean existeISBN(String isbn);
    void salvar(Livro livro);
    void atualizar(int id, String titulo, String autor, String isbn);
    void remover(int id);
 }

