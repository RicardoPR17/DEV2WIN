package com.dev2win.iniciativas.data.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).get();
    }

    public Usuario getUsuarioByNombre(String nombreUsuario) {
        return usuarioRepository.findByNombre(nombreUsuario).get();
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario updateUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getUsuarioId())) {
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

}
