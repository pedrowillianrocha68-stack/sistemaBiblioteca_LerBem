package com.pedrowillianrocha68stack.controller;

import com.pedrowillianrocha68stack.DTO.EmprestimoResponseDTO;
import com.pedrowillianrocha68stack.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Emprestimos", description = "Controle de empréstimos e devoluções")
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Listar todos os empréstimos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<EmprestimoResponseDTO> listarTodos() {
        return emprestimoService.listarTodos();
    }

    @Operation(summary = "Listar empréstimos ativos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/ativos")
    public List<EmprestimoResponseDTO> listarAtivos() {
        return emprestimoService.listarAtivos();
    }

    @Operation(summary = "Listar empréstimos atrasados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/atrasados")
    public List<EmprestimoResponseDTO> listarAtrasados() {
        return emprestimoService.listarAtrasados();
    }

    @Operation(summary = "Realizar empréstimo")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Empréstimo realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Livro indisponível"),
        @ApiResponse(responseCode = "404", description = "Usuário ou livro não encontrado")
    })
    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> realizarEmprestimo(
            @RequestParam Long idUsuario,
            @RequestParam Long idLivro) {
        return ResponseEntity.status(201)
            .body(emprestimoService.realizarEmprestimo(idUsuario, idLivro));
    }

    @Operation(summary = "Registrar devolução")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Devolução registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Livro já foi devolvido"),
        @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    })
    @PutMapping("/devolver/{id}")
    public ResponseEntity<EmprestimoResponseDTO> registrarDevolucao(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.devolverLivro(id));
    }
}