package com.pedrowillianrocha68stack.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="livros")

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @NotBlank(message  = "Título obrigatório")
    @Size(max = 150, message = "Título deve tter no maximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "Autor obrigatorio")
    @Size(max = 100, message = "Autor deve tter no maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String autor;

    @NotBlank(message = "ISBN obrigatorio")
    @Column(nullable = false, unique = true)
    private String isbn;

    @Min(value = 1000, message = "Ano de publicação invalido")
    @Max(value = 2100, message = "Ano de publicação invalido")
    private int anoPublicacao;

    private boolean disponivel;

    @NotBlank(message = "Categoria obrigatoria")
    @Column(nullable = false)
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Livro(){

    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    
    
}
