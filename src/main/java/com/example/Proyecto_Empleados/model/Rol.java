package com.example.Proyecto_Empleados.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.List;

public enum Rol {

    ADMIN,
    EMPLEADO;
// Implementamos el método getAuthorities para que Spring Security pueda obtener los roles de cada usuario
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }

}