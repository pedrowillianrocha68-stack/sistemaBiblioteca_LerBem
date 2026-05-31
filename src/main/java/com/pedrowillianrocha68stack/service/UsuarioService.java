package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> listarTodos() {
        return usuariosRepository.findAll();
    }

    public Usuarios buscarPorId(Long id) {
        return usuariosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuarios salvar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    public Usuarios atualizar(Long id, Usuarios usuarioAtualizado) {
        Usuarios usuario = buscarPorId(id);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setCpf(usuarioAtualizado.getCpf());
        usuario.setTelefone(usuarioAtualizado.getTelefone());
        usuario.setEndereco(usuarioAtualizado.getEndereco());
        return usuariosRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuariosRepository.deleteById(id);
    }
}