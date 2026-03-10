package com.example.Proyecto_Empleados.config;

import com.example.Proyecto_Empleados.model.Rol;
import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UsuarioRepository repo){
        return args -> {

            if (repo.findByCedula("1").isEmpty()) {
    Usuario admin = new Usuario();
    admin.setCedula("1");
    admin.setNombre("Administrador");
    admin.setEmail("admin@admin.com");
    admin.setPassword("1234");
    admin.setRol(Rol.ADMIN);

    repo.save(admin);
}

        };
    }
}