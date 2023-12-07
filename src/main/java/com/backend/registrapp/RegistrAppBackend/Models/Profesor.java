package com.backend.registrapp.RegistrAppBackend.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

// Clase Profesor.java
@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends UserEntity {

    @OneToMany(mappedBy = "profesor")
    private List<QrLogs> qrLogs;
}
