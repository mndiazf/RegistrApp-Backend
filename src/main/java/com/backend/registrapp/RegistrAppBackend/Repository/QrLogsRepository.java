package com.backend.registrapp.RegistrAppBackend.Repository;

import com.backend.registrapp.RegistrAppBackend.Models.Profesor;
import com.backend.registrapp.RegistrAppBackend.Models.QrLogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface QrLogsRepository extends JpaRepository<QrLogs, Long> {
    List<QrLogs> findByProfesorIdAndFecha(Long idProfesor, LocalDate fecha);
}
