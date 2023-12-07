package com.backend.registrapp.RegistrAppBackend.Services;

import com.backend.registrapp.RegistrAppBackend.Models.QrLogs;
import com.backend.registrapp.RegistrAppBackend.Models.Requests.QrLogsResponse;
import com.backend.registrapp.RegistrAppBackend.Repository.QrLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class QrLogsService {

    @Autowired
    private QrLogsRepository qrLogsRepository;

    public List<QrLogsResponse> getQrLogsByProfesorAndFecha(Long idProfesor, LocalDate fecha) {
        List<QrLogs> qrLogsList = qrLogsRepository.findByProfesorIdAndFecha(idProfesor, fecha);

        List<QrLogsResponse> responseList = qrLogsList.stream()
                .map(qrLogs -> new QrLogsResponse(
                        qrLogs.getId(),
                        qrLogs.getUsernameAlumno(),
                        qrLogs.getRutAlumno(),
                        qrLogs.getFecha(),
                        qrLogs.getHora()))
                .collect(Collectors.toList());

        return responseList;
    }
}



