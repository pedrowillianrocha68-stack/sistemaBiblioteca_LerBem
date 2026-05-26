package com.pedrowillianrocha68stack.repository;

import com.pedrowillianrocha68stack.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    Optional<Livro> findByIsbn(String isbn);
    List<Livro> findByCategoria(String categoria);
    List<Livro> findByDisponivelTrue();
    boolean existsByIsbn(String isbn);

    @Query("SELECT 1 FROM Livro 1 WHERE" + "LOWER(1.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR" + "LOWER(1, autor) LIKE LOWER(CONCAT('%', :termo. '%'))")
    List<Livro> buscarPorTitulOuAutor(@Param("termo") String termo);

    @Query("SELECT FROM Livro 1 WHERE 1.categoria = :categoria AND 1.disponivel = true")
    List<Livro> buscarDisponiveisPorCategoria(@Param("categoria") String categoria);
}
