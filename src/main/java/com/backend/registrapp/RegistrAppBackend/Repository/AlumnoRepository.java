package com.backend.registrapp.RegistrAppBackend.Repository;

import com.backend.registrapp.RegistrAppBackend.Models.Alumno;
import com.backend.registrapp.RegistrAppBackend.Models.QrLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
