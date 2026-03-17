package com.example.Proyecto_Empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // ✅ Encripta SOLO si la contraseña no está ya hasheada (no empieza con $2a$)
    public void guardar(Usuario usuario) {
        String pwd = usuario.getPassword();
        if (pwd != null && !pwd.isBlank() && !pwd.startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(pwd));
        }
        usuarioRepository.save(usuario);
    }

    public Usuario buscarPorCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}