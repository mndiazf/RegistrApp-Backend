package com.backend.registrapp.RegistrAppBackend.Models.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QrLogRequest {

    private Long idProfesor;
    private Long idAlumno;

}