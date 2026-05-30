package com.pedrowillianrocha68stack.repository;

import com.pedrowillianrocha68stack.model.Emprestimo;
import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByUsuario(Usuarios usuario); 

    List<Emprestimo> findByUsuario_IdUsuario(Long idUsuario); 

    List<Emprestimo> findByLivro(Livro livro);

    List<Emprestimo> findByDevolvido(boolean devolvido);

    List<Emprestimo> findByDataDevolucao(LocalDate dataDevolucao);

    List<Emprestimo> findByDevolvidoFalseAndDataDevolucaoBefore(LocalDate data);
}