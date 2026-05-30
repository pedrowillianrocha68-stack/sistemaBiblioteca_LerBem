package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.model.Livro;
import com.pedrowillianrocha68stack.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro cadastrar(Livro livro) {
        if (livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new RuntimeException("Já existe um livro com esse ISBN");
        }
        livro.setDisponivel(true); // novo livro começa disponível
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Livro> buscarPorCategoria(String categoria) {
        return livroRepository.findByCategoria(categoria);
    }

    public List<Livro> listarDisponiveis() {
        return livroRepository.findByDisponivelTrue();
    }

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

    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado");
        }
        livroRepository.deleteById(id);
    }
}