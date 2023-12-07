package com.backend.registrapp.RegistrAppBackend.Controller;

import com.backend.registrapp.RegistrAppBackend.Models.Alumno;
import com.backend.registrapp.RegistrAppBackend.Models.Profesor;
import com.backend.registrapp.RegistrAppBackend.Models.Requests.QrLogRequest;
import com.backend.registrapp.RegistrAppBackend.Models.QrLogs;
import com.backend.registrapp.RegistrAppBackend.Repository.AlumnoRepository;
import com.backend.registrapp.RegistrAppBackend.Repository.ProfesorRepository;
import com.backend.registrapp.RegistrAppBackend.Repository.QrLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/qrlogs")
public class QrLogsController {

    @Autowired
    private QrLogsRepository qrLogRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarQrLog(@RequestBody QrLogRequest qrLogRequest) {
        // Busca el profesor y el alumno por sus IDs
        Profesor profesor = profesorRepository.findById(qrLogRequest.getIdProfesor()).orElse(null);
        Alumno alumno = alumnoRepository.findById(qrLogRequest.getIdAlumno()).orElse(null);

        // Verifica si el profesor y el alumno existen
        if (profesor == null || alumno == null) {
            return ResponseEntity.badRequest().body("Profesor o alumno no encontrado");
        }

        // Crea una instancia de QrLog y guarda en la base de datos
        QrLogs qrLog = new QrLogs();
        qrLog.setProfesor(profesor);
        qrLog.setAlumno(alumno);
        qrLog.setUsernameAlumno(alumno.getUsername());
        qrLog.setRutAlumno(alumno.getRut());
        qrLog.setFecha(LocalDate.now());
        qrLog.setHora(LocalTime.now());

        qrLogRepository.save(qrLog);

        return ResponseEntity.ok("QrLog guardado exitosamente");
    }


    @DeleteMapping("/borrar/{idQrLog}")
    public ResponseEntity<String> borrarQrLog(@PathVariable Long idQrLog) {
        // Busca el QrLog por ID
        QrLogs qrLog = qrLogRepository.findById(idQrLog).orElse(null);

        // Verifica si el QrLog existe
        if (qrLog == null) {
            return ResponseEntity.status(404).body("QrLog no encontrado");
        }

        // Borra el QrLog
        qrLogRepository.delete(qrLog);

        return ResponseEntity.ok("QrLog borrado exitosamente");
    }
}

