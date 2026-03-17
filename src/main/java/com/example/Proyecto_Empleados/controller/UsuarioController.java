package com.example.Proyecto_Empleados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar datos del usuario logueado
    @GetMapping("/perfil")
    public String misDatos(@AuthenticationPrincipal User userDetails, Model model) {
        Usuario usuario = usuarioService.buscarPorCedula(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        return "datos";
    }

    // Actualizar datos
@PostMapping("/mis-datos")
public String actualizarDatos(@ModelAttribute Usuario usuarioForm, @AuthenticationPrincipal User userDetails) {
    Usuario usuario = usuarioService.buscarPorCedula(userDetails.getUsername());

    usuario.setNombre(usuarioForm.getNombre());
    usuario.setEmail(usuarioForm.getEmail());

    // Solo actualizar la contraseña si el campo no está vacío.
    // Esto permite que el usuario deje el campo de contraseña vacío si no desea cambiarla.
    if (usuarioForm.getPassword() != null && !usuarioForm.getPassword().isEmpty()) {
        usuario.setPassword(usuarioForm.getPassword());
    }

    usuarioService.guardar(usuario);

    return "redirect:/usuario/perfil";
}
}