package com.RedNorte.SaludRedNorte.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RedNorte.SaludRedNorte.Factory.AtencionFactory;
import com.RedNorte.SaludRedNorte.Factory.AtencionMedica;
import com.RedNorte.SaludRedNorte.Model.PacienteEspera;
import com.RedNorte.SaludRedNorte.Repository.PacienteEsperaRepository;

@Service
public class ListaEsperaService {

    private final PacienteEsperaRepository repository;
    private final AtencionFactory atencionFactory;

    @Autowired
    public ListaEsperaService(PacienteEsperaRepository repository, AtencionFactory atencionFactory) {
        this.repository = repository;
        this.atencionFactory = atencionFactory;
    }

    public PacienteEspera registrarPacienteEnEspera(PacienteEspera paciente, String tipoAtencion) {
        AtencionMedica atencion = atencionFactory.crearAtencion(tipoAtencion);
        System.out.println(atencion.procesarAtencion(paciente.getRut()));
        paciente.setNivelPrioridad(atencion.obtenerNivelPrioridadBase());
        return repository.save(paciente);
    }

    public List<PacienteEspera> obtenerTodosLosPacientes() {
        return repository.findAll();
    }
}