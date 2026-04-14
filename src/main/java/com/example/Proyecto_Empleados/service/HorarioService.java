
package com.example.Proyecto_Empleados.service;

import com.example.Proyecto_Empleados.model.Horario;
import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    // Registrar entrada
    public void registrarEntrada(Usuario usuario) {
        Optional<Horario> activo = horarioRepository.findByUsuarioAndSalidaIsNull(usuario);

        if (activo.isPresent()) {
            throw new IllegalStateException("Ya tienes una entrada registrada sin salida.");
        }

        Horario horario = new Horario();
        horario.setUsuario(usuario);
        horario.setEntrada(LocalDateTime.now());
        horarioRepository.save(horario);
    }

    // Registrar salida
    public void registrarSalida(Usuario usuario) {
        Horario activo = horarioRepository.findByUsuarioAndSalidaIsNull(usuario)
            .orElseThrow(() -> new IllegalStateException("No tienes una entrada activa para registrar salida."));

        activo.setSalida(LocalDateTime.now());

        if (activo.getSalida().isBefore(activo.getEntrada())) {
            throw new IllegalStateException("La salida no puede ser antes de la entrada.");
        }

        horarioRepository.save(activo);
    }

    // Historial de un usuario
    public List<Horario> obtenerPorUsuario(Usuario usuario) {
        return horarioRepository.findByUsuarioOrderByEntradaDesc(usuario);
    }

    // Todos los horarios (admin)
    public List<Horario> obtenerTodos() {
        return horarioRepository.findAllByOrderByEntradaDesc();
    }

    // Verificar si tiene entrada activa
    public boolean tieneEntradaActiva(Usuario usuario) {
        return horarioRepository.findByUsuarioAndSalidaIsNull(usuario).isPresent();
    }
}
