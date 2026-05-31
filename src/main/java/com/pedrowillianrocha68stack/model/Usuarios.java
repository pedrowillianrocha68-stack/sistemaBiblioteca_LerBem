package com.pedrowillianrocha68stack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotBlank(message = "Nome obrigatorio")
    @Size(max = 100, message = "Nome deve ter no maximo 100 caracteres")
    @Column(nullable = false, length =  100)
    private String nome;

    @NotBlank(message = "Email obrigatorio")
    @Email(message = "Email invalido")
    @Column (nullable = false, unique = true)
    private String email;

    @NotBlank(message = "CPF obrigatorio")
    @Size(min = 11, max = 14, message = "CPF invalido")
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Size(max = 20)
    private String telefone;

    @Size(max = 200)
    private String endereco;

    @NotBlank(message = "Senha obrigatoria")
    @Size(min = 6, message = "a senha deve ter no minimo 6 caracteres")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuarios(){

    }

    public Long getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario (Long idUsuario){
        this.idUsuario = idUsuario;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone (String telefone){
        this.telefone = telefone;
    }
    public String getEndereco(){
        return endereco;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

}
