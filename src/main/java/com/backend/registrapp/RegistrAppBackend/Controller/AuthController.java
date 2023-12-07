package com.backend.registrapp.RegistrAppBackend.Controller;

import com.backend.registrapp.RegistrAppBackend.Models.*;
import com.backend.registrapp.RegistrAppBackend.Models.Requests.LoginRequest;
import com.backend.registrapp.RegistrAppBackend.Models.Requests.UserInfoResponse;
import com.backend.registrapp.RegistrAppBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> login(@RequestBody LoginRequest loginRequest) {
        // Encuentra el usuario por nombre de usuario
        UserEntity usuario = usuarioRepository.findByUsername(loginRequest.getUsername());

        // Verifica si el usuario existe y si la contraseña es correcta
        if (usuario != null && passwordEncoder.matches(loginRequest.getPassword(), usuario.getContraseña())) {
            // Construye la respuesta
            UserInfoResponse userInfoResponse = new UserInfoResponse();
            userInfoResponse.setId(usuario.getId());
            userInfoResponse.setUsername(usuario.getUsername());

            if (usuario instanceof Profesor) {
                userInfoResponse.setUserType("Profesor");
            } else if (usuario instanceof Alumno) {
                userInfoResponse.setUserType("Alumno");
            } else {
                userInfoResponse.setUserType("Usuario Desconocido");
            }

            return ResponseEntity.ok(userInfoResponse);
        } else {
            // Si el usuario o la contraseña son incorrectos, retorna un código de respuesta no autorizado
            return ResponseEntity.status(401).build();
        }
    }
}
