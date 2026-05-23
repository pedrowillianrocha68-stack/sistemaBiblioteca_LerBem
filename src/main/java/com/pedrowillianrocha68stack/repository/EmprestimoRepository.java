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

    // Todos os empréstimos de um usuário
    List<Emprestimo> findByUsuario(Usuarios usuario); 

    // Todos os empréstimos de um usuário pelo ID
    List<Emprestimo> findByUsuario_IdUsuario(Long idUsuario); 

    // Todos os empréstimos de um livro
    List<Emprestimo> findByLivro(Livro livro);

    // Empréstimos ativos (não devolvidos)
    List<Emprestimo> findByDevolvido(boolean devolvido);

    // Empréstimos por data de devolução
    List<Emprestimo> findByDataDevolucao(LocalDate dataDevolucao);

    // Empréstimos atrasados (não devolvidos e com data de devolução antes de hoje)
    List<Emprestimo> findByDevolvidoFalseAndDataDevolucaoBefore(LocalDate data);
}