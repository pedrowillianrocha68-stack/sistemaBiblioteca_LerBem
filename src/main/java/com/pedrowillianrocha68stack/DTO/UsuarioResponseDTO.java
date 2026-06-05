package com.pedrowillianrocha68stack.DTO;

public record UsuarioResponseDTO(
    Long idUsuario,
    String nome,
    String email,
    String cpf,
    String telefone,
    String endereco
) {}