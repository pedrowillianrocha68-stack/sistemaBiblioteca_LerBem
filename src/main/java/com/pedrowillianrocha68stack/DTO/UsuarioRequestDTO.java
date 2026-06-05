package com.pedrowillianrocha68stack.DTO;

import jakarta.validation.constraints.*;

public record UsuarioRequestDTO(

    @NotBlank(message = "Nome obrigatório")
    @Size(max = 100)
    String nome,

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "CPF obrigatório")
    @Size(min = 11, max = 14)
    String cpf,

    @Size(max = 20)
    String telefone,

    @Size(max = 200)
    String endereco,

    @NotBlank(message = "Senha obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    String senha

) {}