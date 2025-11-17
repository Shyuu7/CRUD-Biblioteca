package com.br.infnet.repository.implementations;

import com.br.infnet.model.Emprestimo;
import com.br.infnet.model.Livro;
import com.br.infnet.repository.interfaces.iEmprestimoRepository;
import com.br.infnet.service.EmprestimoService;
import com.br.infnet.service.LivroService;

import java.time.LocalDate;
import java.util.*;

public class EmprestimoRepositoryImpl implements iEmprestimoRepository {
    private final LivroService livroService;
    private final EmprestimoService emprestimoService;

    public EmprestimoRepositoryImpl(LivroService livroService, EmprestimoService emprestimoService) {
        this.livroService = livroService;
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
    public void validarPrazoEmprestimo(int prazo) {
        emprestimoService.
    }

    @Override
    public void removerEmprestimo(int id) {

    }

    @Override
    public void calcularMulta(Emprestimo emprestimo) {

    }

    @Override
    public void calcularDiasAtraso(Emprestimo emprestimo) {

    }

    @Override
    public void atualizarDadosAposEmprestimo(Emprestimo emprestimo) {

    }

    @Override
    public void atualizarDadosAposDevolucao(Emprestimo emprestimo) {

    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.listarEmprestimos();
    }

    private void atualizarDadosEmprestimoNoLivro(Emprestimo emprestimo) {
        Livro livro = livroService.buscarLivroPorIDNoAcervo(emprestimo.getLivroId());
        livro.setDataEmprestimo(emprestimo.getDataEmprestimo());
        livro.setPrazoDevolucao(emprestimo.getPrazoDevolucao());
        livro.setDataEstimadaDevolucao(emprestimo.getDataEstimadaDevolucao());
        livro.setDisponivel(false);
    }

    private void limparDadosEmprestimoNoLivro(int livroId) {
        Livro livro = livroService.buscarLivroPorIDNoAcervo(livroId);
        livro.setDataEmprestimo(null);
        livro.setPrazoDevolucao(0);
        livro.setDataEstimadaDevolucao(null);
        livro.setDataEfetivaDevolucao(null);
        livro.setMulta(0);
        livro.setDisponivel(true);
    }
}
