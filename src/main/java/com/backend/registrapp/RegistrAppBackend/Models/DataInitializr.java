package com.backend.registrapp.RegistrAppBackend.Models;

import com.backend.registrapp.RegistrAppBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr implements CommandLineRunner {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si los usuarios de prueba ya existen
        if (usuarioRepository.findByUsername("profesor") == null) {
            Profesor profesor = new Profesor();
            profesor.setRut("12345678-9");
            profesor.setUsername("profesor");
            profesor.setContraseña(passwordEncoder.encode("1234"));
            usuarioRepository.save(profesor);
        }

        if (usuarioRepository.findByUsername("alumno1") == null) {
            Alumno alumno1 = new Alumno();
            alumno1.setRut("98765432-1");
            alumno1.setUsername("alumno1");
            alumno1.setContraseña(passwordEncoder.encode("1234"));
            usuarioRepository.save(alumno1);
        }

        if (usuarioRepository.findByUsername("alumno2") == null) {
            Alumno alumno2 = new Alumno();
            alumno2.setRut("54321098-7");
            alumno2.setUsername("alumno2");
            alumno2.setContraseña(passwordEncoder.encode("1234"));
            usuarioRepository.save(alumno2);
        }
    }
}
