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

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    
    @Transactional
    public Livro cadastrar(Livro livro) {
        if (livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new RuntimeException("Já existe um livro com esse ISBN");
        }
        livro.setDisponivel(true); // novo livro começa disponível
        return livroRepository.save(livro);
    }

    @Transactional(readOnly = true)
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    @Transactional(readOnly = true)
    public List<Livro> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }
    
    @Transactional(readOnly = true)
    public List<Livro> buscarPorCategoria(String categoria) {
        return livroRepository.findByCategoria(categoria);
    }

    @Transactional(readOnly = true)
    public List<Livro> listarDisponiveis() {
        return livroRepository.findByDisponivelTrue();
    }
    
    @Transactional
    public Livro atualizar(Long id, Livro dadosNovos) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(dadosNovos.getTitulo());
        livro.setAutor(dadosNovos.getAutor());
        livro.setIsbn(dadosNovos.getIsbn());
        livro.setAnoPublicacao(dadosNovos.getAnoPublicacao());
        livro.setCategoria(dadosNovos.getCategoria());
        livro.setDisponivel(dadosNovos.isDisponivel());

        return livroRepository.save(livro);
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