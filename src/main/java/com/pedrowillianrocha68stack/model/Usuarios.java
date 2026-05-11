package com.pedrowillianrocha68stack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nome;
    private String email;
    private int cpf;
    private String telefone;
    private String endereco;

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
    public int getCpf(){
        return cpf;
    }
    public void setCpf(int cpf){
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
