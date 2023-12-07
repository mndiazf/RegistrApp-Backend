package com.backend.registrapp.RegistrAppBackend.Repository;

import com.backend.registrapp.RegistrAppBackend.Models.Profesor;
import com.backend.registrapp.RegistrAppBackend.Models.QrLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
