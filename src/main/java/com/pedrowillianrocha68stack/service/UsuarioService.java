package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Cadastrar novo usuário
    public Usuarios cadastrar(Usuarios usuario) {
        if (usuariosRepository.existsByNome(usuario.getNome())) {
            throw new RuntimeException("Usuário já existe com esse nome");
        }
        if (usuariosRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuariosRepository.save(usuario);
    }

    // Listar todos os usuários
    public List<Usuarios> listarTodos() {
        return usuariosRepository.findAll();
    }

    // Buscar por ID
    public Optional<Usuarios> buscarPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    // Buscar por nome
    public Optional<Usuarios> buscarPorNome(String nome) {
        return usuariosRepository.findByNome(nome);
    }

    // Buscar por email
    public Optional<Usuarios> buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    // Atualizar usuário
    public Usuarios atualizar(Long id, Usuarios dadosNovos) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dadosNovos.getNome());
        usuario.setEmail(dadosNovos.getEmail());
        usuario.setCpf(dadosNovos.getCpf());
        usuario.setTelefone(dadosNovos.getTelefone());
        usuario.setEndereco(dadosNovos.getEndereco());

        // Só atualiza senha se uma nova foi informada
        if (dadosNovos.getSenha() != null && !dadosNovos.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dadosNovos.getSenha()));
        }

        return usuariosRepository.save(usuario);
    }

    // Deletar usuário
    public void deletar(Long id) {
        if (!usuariosRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuariosRepository.deleteById(id);
    }
}