package com.pedrowillianrocha68stack.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="emprestimo")

public class Emprestimo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    @ManyToOne
    @JoinColumn(name = "IdLivro")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuarios usuario;

  
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    public Emprestimo(){
    }
    
    public Long getIdEmprestimo(){
        return iidEmprestimo;
    }
    public void setIdEmprestimo(Long idEmprestimo){
        this.idEmprestimo = idEmprestimo;
    }
    public Livro getLivro(){
        return livro;
    }
    public void setLIvro (Livro livro){
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
    public LocalDate gatDataDevolucao(){
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