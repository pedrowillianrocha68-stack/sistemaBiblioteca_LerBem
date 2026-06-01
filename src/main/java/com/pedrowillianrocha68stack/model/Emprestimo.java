package com.pedrowillianrocha68stack.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
@Table(name="emprestimo")

public class Emprestimo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    @NotNull(message = "Livro obrigatorio")
    @ManyToOne
    @JoinColumn(name = "IdLivro")
    private Livro livro;
    
    @NotNull(message = "Usuário obrigatorio")
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuarios usuario;

    @NotNull(message = "Data de empréstimo obrigatória")
    private LocalDate dataEmprestimo;

    @NotNull(message= "Data de devolução obrigatória")
    @Future(message = "Data de devolução deve ser futura")
    private LocalDate dataDevolucao;
    private boolean devolvido;

    public Emprestimo(){
    }
    
    public Long getIdEmprestimo(){
        return idEmprestimo;
    }
    public void setIdEmprestimo(Long idEmprestimo){
        this.idEmprestimo = idEmprestimo;
    }
    public Livro getLivro(){
        return livro;
    }
    public void setLivro (Livro livro){
        this.livro = livro;
    }
    public Usuarios getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuarios usuario){
        this.usuario = usuario;
    }
    public LocalDate getDataEmprestimo(){
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo){
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataDevolucao(){
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }
    public boolean isDevolvido(){
        return devolvido;
    }
    public void setDevolvido (boolean devolvido){
        this.devolvido = devolvido;
    }

}