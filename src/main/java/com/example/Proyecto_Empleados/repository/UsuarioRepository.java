package com.example.Proyecto_Empleados.repository;

import java.util.Optional;

import com.example.Proyecto_Empleados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
// Método para buscar un usuario por su cédula 
    Optional<Usuario> findByCedula(String cedula);

}