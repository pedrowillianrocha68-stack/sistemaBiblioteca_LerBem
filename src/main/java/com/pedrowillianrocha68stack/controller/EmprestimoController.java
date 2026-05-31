package com.pedrowillianrocha68stack.controller;

import com.pedrowillianrocha68stack.model.Emprestimo;
import com.pedrowillianrocha68stack.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimoService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<Emprestimo> listarAtivos() {
        return emprestimoService.listarAtivos();
    }

    @PostMapping
    public ResponseEntity<Emprestimo> registrarEmprestimo(@RequestBody Emprestimo emprestimo) {
        return ResponseEntity.ok(emprestimoService.registrarEmprestimo(emprestimo));
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Emprestimo> registrarDevolucao(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.registrarDevolucao(id));
    }
}