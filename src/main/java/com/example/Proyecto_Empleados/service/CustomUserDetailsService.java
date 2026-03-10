package com.example.Proyecto_Empleados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByCedula(cedula)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con cédula: " + cedula));

    return org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getCedula())
            .password("{noop}" + usuario.getPassword()) // para pruebas sin encriptar
            .authorities(usuario.getRol().getAuthorities())
            .build();
}
}