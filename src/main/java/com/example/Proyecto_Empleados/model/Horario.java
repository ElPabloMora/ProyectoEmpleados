package com.example.Proyecto_Empleados.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime entrada;
    private LocalDateTime salida;

    public Horario() {}

    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDateTime getEntrada() { return entrada; }
    public void setEntrada(LocalDateTime entrada) { this.entrada = entrada; }

    public LocalDateTime getSalida() { return salida; }
    public void setSalida(LocalDateTime salida) { this.salida = salida; }

    // Estado del empleado
    public String getEstado() {
        return (salida == null) ? "En línea" : "Fuera de línea";
    }
}
