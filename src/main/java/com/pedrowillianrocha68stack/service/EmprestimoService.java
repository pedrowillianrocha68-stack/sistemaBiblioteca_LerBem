package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.model.Emprestimo;
import com.pedrowillianrocha68stack.model.Livro;
import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.repository.EmprestimoRepository;
import com.pedrowillianrocha68stack.repository.LivroRepository;
import com.pedrowillianrocha68stack.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Emprestimo realizarEmprestimo(Long idUsuario, Long idLivro) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro não está disponível para empréstimo");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(14)); // 14 dias para devolver
        emprestimo.setDevolvido(false);

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolverLivro(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (emprestimo.isDevolvido()) {
            throw new RuntimeException("Livro já foi devolvido");
        }

        emprestimo.setDevolvido(true);
        emprestimo.getLivro().setDisponivel(true);
        livroRepository.save(emprestimo.getLivro());

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> listarPorUsuario(Long idUsuario) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return emprestimoRepository.findByUsuario(usuario);
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByDevolvido(false);
    }

    public List<Emprestimo> listarAtrasados() {
        return emprestimoRepository.findByDevolvidoFalseAndDataDevolucaoBefore(LocalDate.now());
    }
}