package com.RedNorte.SaludRedNorte.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RedNorte.SaludRedNorte.Model.Cita;
import com.RedNorte.SaludRedNorte.Repository.CitaRepository;

@Service
public class CitaService {

    private final CitaRepository repository;

    @Autowired
    public CitaService(CitaRepository repository) {
        this.repository = repository;
    }

    public Cita agendarCita(Cita cita) {
        cita.setEstado("PROGRAMADA");
        return repository.save(cita);
    }

    public Cita cancelarCita(Long idCita) {
        Cita cita = repository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el ID: " + idCita));
        
        cita.setEstado("CANCELADA");
        Cita citaCancelada = repository.save(cita);

        
        return citaCancelada;
    }

    public List<Cita> obtenerCitasPorPaciente(String rut) {
        return repository.findByRutPaciente(rut);
    }
}