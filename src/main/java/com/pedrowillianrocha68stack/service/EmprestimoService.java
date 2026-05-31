package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.model.Emprestimo;
import com.pedrowillianrocha68stack.model.Livro;
import com.pedrowillianrocha68stack.repository.EmprestimoRepository;
import com.pedrowillianrocha68stack.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByDevolvido(false);
    }

    public Emprestimo registrarEmprestimo(Emprestimo emprestimo) {
        Livro livro = emprestimo.getLivro();
        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro não está disponível");
        }
        livro.setDisponivel(false);
        livroRepository.save(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDevolvido(false);
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        emprestimo.setDevolvido(true);
        emprestimo.setDataDevolucao(LocalDate.now());
        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);
        return emprestimoRepository.save(emprestimo);
    }
}