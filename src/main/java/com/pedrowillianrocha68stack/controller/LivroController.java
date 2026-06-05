package com.pedrowillianrocha68stack.controller;

import com.pedrowillianrocha68stack.DTO.LivroRequestDTO;
import com.pedrowillianrocha68stack.DTO.LivroResponseDTO;
import com.pedrowillianrocha68stack.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Livros", description = "Gerenciamento do acervo da biblioteca")
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Listar todos os livros")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos();
    }

    @Operation(summary = "Buscar livro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar livros por título")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/buscar")
    public List<LivroResponseDTO> buscarPorTitulo(@RequestParam String titulo) {
        return livroService.buscarPorTitulo(titulo);
    }

    @Operation(summary = "Cadastrar novo livro")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou ISBN duplicado")
    })
    @PostMapping
    public ResponseEntity<LivroResponseDTO> salvar(@Valid @RequestBody LivroRequestDTO dto) {
        return ResponseEntity.status(201).body(livroService.cadastrar(dto));
    }

    @Operation(summary = "Atualizar livro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto) {
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @Operation(summary = "Deletar livro")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}