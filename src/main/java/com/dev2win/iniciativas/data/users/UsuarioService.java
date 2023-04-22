package com.dev2win.iniciativas.data.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UserRepository usuarioRepository;

    @Autowired
    public UsuarioService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public User addUser(User usuario) {
        return usuarioRepository.save(usuario);
    }

    public User getUser(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).get();
    }

    public User getUserByName(String nombreUsuario) {
        return usuarioRepository.findByName(nombreUsuario).get();
    }

    public List<User> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public User updateUsuario(User usuario) {
        if (usuarioRepository.existsById(usuario.getUserId())) {
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    public List<User> getUserByProfile(String perfilBuscado) {
        return usuarioRepository.findByProfile(perfilBuscado);
    }

    public List<User> getUsuariosPorPerfil(String perfilBuscado) {
        return usuarioRepository.findByProfile(perfilBuscado);
    }
}
