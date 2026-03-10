package com.example.Proyecto_Empleados.repository;

import java.util.Optional;

import com.example.Proyecto_Empleados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByCedula(String cedula);

}