package com.example.Proyecto_Empleados.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Empleados.model.Rol;
import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.service.UsuarioService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
// Lista todos los empleados
    @GetMapping("/usuarios")
public String listarUsuarios(Model model) {
    model.addAttribute("usuarios", usuarioService.listarUsuarios());
    model.addAttribute("nuevoUsuario", new Usuario()); // ✅ Para el formulario
    return "admin/usuarios";
}

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/nuevoUsuario";
    }


    // ✅ Guardar nuevo usuario
    @PostMapping("/usuarios/nuevo")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }


    // Formulario para editar usuario
    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "admin/editarUsuario";
    }

    // Guardar cambios (update)
    @PostMapping("/usuarios/actualizar")
        public String actualizarUsuario(@RequestParam Long id,
                                @RequestParam String nombre,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam Rol rol) {

    Usuario usuarioExistente = usuarioService.buscarPorId(id);

    usuarioExistente.setNombre(nombre);
    usuarioExistente.setEmail(email);
    usuarioExistente.setRol(rol);

    // Solo actualizar la contraseña si el campo no está vacío
    if (password != null && !password.isEmpty()) {
        usuarioExistente.setPassword(password);
    }

    usuarioService.guardar(usuarioExistente);

    return "redirect:/admin/usuarios";
}


// Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id){

        usuarioService.eliminar(id);

        return "redirect:/admin/usuarios";
    }
}