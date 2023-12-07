package com.backend.registrapp.RegistrAppBackend.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

// Clase Alumno.java
@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends UserEntity {

    @OneToMany(mappedBy = "alumno")
    private List<QrLogs> qrLogs;
}
