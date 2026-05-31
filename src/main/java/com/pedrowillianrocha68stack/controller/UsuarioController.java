package com.pedrowillianrocha68stack.controller;

import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuarios> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id). get());
    }

    @PostMapping
    public ResponseEntity<Usuarios> salvar(@RequestBody Usuarios usuario) {
       Usuarios usuarioSalvo = usuarioService.cadastrar(usuario);
        return ResponseEntity.status(201).body(usuarioSalvo);    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> atualizar(@PathVariable Long id, @RequestBody Usuarios usuario) {
        return ResponseEntity.ok(usuarioService.atualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}