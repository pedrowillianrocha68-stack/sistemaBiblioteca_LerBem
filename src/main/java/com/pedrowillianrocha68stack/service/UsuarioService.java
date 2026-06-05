package com.pedrowillianrocha68stack.service;

import com.pedrowillianrocha68stack.DTO.UsuarioRequestDTO;
import com.pedrowillianrocha68stack.DTO.UsuarioResponseDTO;
import com.pedrowillianrocha68stack.model.Usuarios;
import com.pedrowillianrocha68stack.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuariosRepository.existsByNome(dto.nome())) {
            throw new RuntimeException("Usuário já existe com esse nome");
        }
        if (usuariosRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado");
        }
        Usuarios usuario = toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        return toDTO(usuariosRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuariosRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> buscarPorId(Long id) {
        return usuariosRepository.findById(id).map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> buscarPorNome(String nome) {
        return usuariosRepository.findByNome(nome).map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email).map(this::toDTO);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setEndereco(dto.endereco());
        if (dto.senha() != null && !dto.senha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }
        return toDTO(usuariosRepository.save(usuario));
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuariosRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuariosRepository.deleteById(id);
    }

    private UsuarioResponseDTO toDTO(Usuarios usuario) {
        return new UsuarioResponseDTO(
            usuario.getIdUsuario(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getCpf(),
            usuario.getTelefone(),
            usuario.getEndereco()
        );
    }

    private Usuarios toEntity(UsuarioRequestDTO dto) {
        Usuarios usuario = new Usuarios();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setEndereco(dto.endereco());
        usuario.setSenha(dto.senha());
        return usuario;
    }
}
