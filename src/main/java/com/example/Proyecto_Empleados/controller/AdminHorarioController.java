package com.example.Proyecto_Empleados.controller;

import com.example.Proyecto_Empleados.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminHorarioController {

    @Autowired
    private HorarioService horarioService;

    // Ver todos los horarios
    @GetMapping("/horarios")
    public String verTodosHorarios(Model model) {
        model.addAttribute("horarios", horarioService.obtenerTodos());
        return "admin/horarios";
    }
}