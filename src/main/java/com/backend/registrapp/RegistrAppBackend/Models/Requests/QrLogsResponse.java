package com.backend.registrapp.RegistrAppBackend.Models.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QrLogsResponse {

    private Long id_asistencia;
    private String usernameAlumno;
    private String rutAlumno;
    private LocalDate fecha;
    private LocalTime hora;
}
