package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.DTO.EmprestimoResponseDTO;
import com.pedrowillianrocha68stack.model.Emprestimo;
import com.pedrowillianrocha68stack.model.Livro;
import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.repository.EmprestimoRepository;
import com.pedrowillianrocha68stack.repository.LivroRepository;
import com.pedrowillianrocha68stack.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Transactional
    public EmprestimoResponseDTO realizarEmprestimo(Long idUsuario, Long idLivro) {
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
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(14));
        emprestimo.setDevolvido(false);

        return toDTO(emprestimoRepository.save(emprestimo));
    }

    @Transactional
    public EmprestimoResponseDTO devolverLivro(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (emprestimo.isDevolvido()) {
            throw new RuntimeException("Livro já foi devolvido");
        }

        emprestimo.setDevolvido(true);
        emprestimo.getLivro().setDisponivel(true);
        livroRepository.save(emprestimo.getLivro());

        return toDTO(emprestimoRepository.save(emprestimo));
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> listarTodos() {
        return emprestimoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<EmprestimoResponseDTO> buscarPorId(Long id) {
        return emprestimoRepository.findById(id).map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> listarPorUsuario(Long idUsuario) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return emprestimoRepository.findByUsuario(usuario)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> listarAtivos() {
        return emprestimoRepository.findByDevolvido(false)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> listarAtrasados() {
        return emprestimoRepository.findByDevolvidoFalseAndDataDevolucaoBefore(LocalDate.now())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private EmprestimoResponseDTO toDTO(Emprestimo emprestimo) {
        return new EmprestimoResponseDTO(
            emprestimo.getIdEmprestimo(),
            emprestimo.getUsuario().getNome(),
            emprestimo.getLivro().getTitulo(),
            emprestimo.getLivro().getIsbn(),
            emprestimo.getDataEmprestimo(),
            emprestimo.getDataDevolucao(),
            emprestimo.isDevolvido()
        );
    }
}