package com.pedrowillianrocha68stack.repository;

import com.pedrowillianrocha68stack.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{

    Optional<Usuarios> findByNome(String nome);

    Optional<Usuarios> findByEmail(String email);

    boolean existsByNome(String nome);
    boolean existsByEmail(String email);

    List<Usuarios> findByActiveTrue();

    List<Usuarios> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT u FROM Usuarios u JOIN u.roles r WHERE r.name = :rolename")
    List<Usuarios> findByRoleName(@Param("rolename") String rolename);
}
