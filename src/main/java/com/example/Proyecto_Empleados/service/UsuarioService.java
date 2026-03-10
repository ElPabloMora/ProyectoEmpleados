package com.example.Proyecto_Empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto_Empleados.model.Usuario;
import com.example.Proyecto_Empleados.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }



    
    public Usuario buscarPorCedula(String cedula) {
    return usuarioRepository.findByCedula(cedula)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
}
   public Usuario buscarPorId(Long id) {
    return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
}

    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

}