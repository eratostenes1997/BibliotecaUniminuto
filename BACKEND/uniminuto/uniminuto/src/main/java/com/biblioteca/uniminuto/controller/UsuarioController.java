package com.biblioteca.uniminuto.controller;

import com.biblioteca.uniminuto.models.Usuario;
import com.biblioteca.uniminuto.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1"})  // Permitir solicitudes desde ambos orígenes
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public String login(@RequestBody Usuario loginUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(loginUsuario.getUsername());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(loginUsuario.getPassword())) {
                return "Login exitoso";
            } else {
                return "Contraseña incorrecta";
            }
        } else {
            return "Usuario no encontrado";
        }
    }
}
