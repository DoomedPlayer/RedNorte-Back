package com.RedNorte.SaludRedNorte.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RedNorte.SaludRedNorte.Model.Paciente;
import com.RedNorte.SaludRedNorte.Repository.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    @Autowired
    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente registrarOActualizarPaciente(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente obtenerPacientePorRut(String rut) {
        return repository.findById(rut)
                .orElseThrow(() -> new RuntimeException("Paciente con RUT " + rut + " no encontrado en el sistema central."));
    }
}
