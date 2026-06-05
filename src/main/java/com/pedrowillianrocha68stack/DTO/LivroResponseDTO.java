package com.pedrowillianrocha68stack.DTO;

public record LivroResponseDTO(
    Long idLivro,
    String titulo,
    String autor,
    String isbn,
    int anoPublicacao,
    String categoria,
    boolean disponivel
) {}