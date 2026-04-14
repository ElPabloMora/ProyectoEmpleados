package com.example.Proyecto_Empleados.controller;

import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.service.HorarioService;
import com.example.Proyecto_Empleados.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private UsuarioService usuarioService;

    // Ver historial del empleado logueado
    @GetMapping("/horarios")
    public String verHorarios(@AuthenticationPrincipal User userDetails, Model model) {
        Usuario usuario = usuarioService.buscarPorCedula(userDetails.getUsername());
        model.addAttribute("horarios", horarioService.obtenerPorUsuario(usuario));
        model.addAttribute("tieneEntradaActiva", horarioService.tieneEntradaActiva(usuario));
        return "usuario/horarios";
    }

    // Registrar entrada
    @PostMapping("/entrada")
    public String registrarEntrada(@AuthenticationPrincipal User userDetails,
                                   RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.buscarPorCedula(userDetails.getUsername());
            horarioService.registrarEntrada(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Entrada registrada correctamente.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/horarios";
    }

    // Registrar salida
    @PostMapping("/salida")
    public String registrarSalida(@AuthenticationPrincipal User userDetails,
                                  RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.buscarPorCedula(userDetails.getUsername());
            horarioService.registrarSalida(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Salida registrada correctamente.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/horarios";
    }
}