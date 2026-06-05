package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.DTO.LivroRequestDTO;
import com.pedrowillianrocha68stack.DTO.LivroResponseDTO;
import com.pedrowillianrocha68stack.model.Livro;
import com.pedrowillianrocha68stack.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        if (livroRepository.existsByIsbn(dto.isbn())) {
            throw new RuntimeException("Já existe um livro com esse ISBN");
        }
        Livro livro = toEntity(dto);
        livro.setDisponivel(true);
        return toDTO(livroRepository.save(livro));
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<LivroResponseDTO> buscarPorId(Long id) {
        return livroRepository.findById(id).map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarPorCategoria(String categoria) {
        return livroRepository.findByCategoria(categoria)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> listarDisponiveis() {
        return livroRepository.findByDisponivelTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setCategoria(dto.categoria());
        return toDTO(livroRepository.save(livro));
    }

    @Transactional
    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado");
        }
        livroRepository.deleteById(id);
    }

    private LivroResponseDTO toDTO(Livro livro) {
        return new LivroResponseDTO(
            livro.getIdLivro(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getCategoria(),
            livro.isDisponivel()
        );
    }

    private Livro toEntity(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setCategoria(dto.categoria());
        return livro;
    }
}

