package com.pedrowillianrocha68stack.DTO;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
    Long idEmprestimo,
    String nomeUsuario,
    String tituloLivro,
    String isbnLivro,
    LocalDate dataEmprestimo,
    LocalDate dataDevolucao,
    boolean devolvido
) {}