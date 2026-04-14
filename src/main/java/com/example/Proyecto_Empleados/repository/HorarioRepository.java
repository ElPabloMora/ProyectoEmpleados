package com.example.Proyecto_Empleados.repository;

import com.example.Proyecto_Empleados.model.Horario;
import com.example.Proyecto_Empleados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Todos los horarios de un usuario
    List<Horario> findByUsuarioOrderByEntradaDesc(Usuario usuario);

    // Todos los horarios (para admin)
    List<Horario> findAllByOrderByEntradaDesc();

    // Buscar entrada activa (sin salida) de un usuario
    Optional<Horario> findByUsuarioAndSalidaIsNull(Usuario usuario);
}