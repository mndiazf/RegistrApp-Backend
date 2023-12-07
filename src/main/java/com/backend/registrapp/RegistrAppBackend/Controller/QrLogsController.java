package com.backend.registrapp.RegistrAppBackend.Controller;

import com.backend.registrapp.RegistrAppBackend.Models.Alumno;
import com.backend.registrapp.RegistrAppBackend.Models.Profesor;
import com.backend.registrapp.RegistrAppBackend.Models.Requests.QrLogRequest;
import com.backend.registrapp.RegistrAppBackend.Models.QrLogs;
import com.backend.registrapp.RegistrAppBackend.Models.Requests.QrLogsResponse;
import com.backend.registrapp.RegistrAppBackend.Repository.AlumnoRepository;
import com.backend.registrapp.RegistrAppBackend.Repository.ProfesorRepository;
import com.backend.registrapp.RegistrAppBackend.Repository.QrLogsRepository;
import com.backend.registrapp.RegistrAppBackend.Services.QrLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/qrlogs")
public class QrLogsController {

    @Autowired
    private QrLogsRepository qrLogRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private QrLogsService qrLogsService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarQrLog(@RequestBody QrLogRequest qrLogRequest) {
        // Busca el profesor y el alumno por sus IDs
        Profesor profesor = profesorRepository.findById(qrLogRequest.getIdProfesor()).orElse(null);
        Alumno alumno = alumnoRepository.findById(qrLogRequest.getIdAlumno()).orElse(null);

        // Verifica si el profesor y el alumno existen
        if (profesor == null || alumno == null) {
            return ResponseEntity.badRequest().body("Profesor o alumno no encontrado");
        }

        // Verifica si ya existe un registro para el mismo alumno y fecha
        boolean qrLogExists = qrLogRepository.existsByAlumnoAndFecha(alumno, LocalDate.now());

        if (qrLogExists) {
            return ResponseEntity.badRequest().body("Ya existe un registro para el alumno en la fecha actual");
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


    @GetMapping("/by-profesor")
    public ResponseEntity<List<QrLogsResponse>> getQrLogsByProfesorAndFecha(
            @RequestParam Long idProfesor,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        List<QrLogsResponse> responseList = qrLogsService.getQrLogsByProfesorAndFecha(idProfesor, fecha);

        if (responseList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseList);
    }
}

