package com.example.Proyecto_Empleados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Empleados.model.Rol;
import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.service.UsuarioService;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
// Mostrar formulario de registro
    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Guardar nuevo usuario con rol EMPLEADO por defecto
    @PostMapping("/registro")
    public String guardarUsuario(@ModelAttribute Usuario usuario){

        usuario.setRol(Rol.EMPLEADO);

        usuarioService.guardar(usuario);

        return "redirect:/login";
    }
}
