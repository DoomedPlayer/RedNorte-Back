package com.RedNorte.SaludRedNorte.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RedNorte.SaludRedNorte.Model.RegistroReasignacion;
import com.RedNorte.SaludRedNorte.Repository.ReasignacionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ReasignacionService {

    private final ReasignacionRepository repository;

    @Autowired
    public ReasignacionService(ReasignacionRepository repository) {
        this.repository = repository;
    }

    @CircuitBreaker(name = "waitlistService", fallbackMethod = "fallbackBuscarSiguientePaciente")
    public RegistroReasignacion procesarCancelacion(Long idCitaCancelada, String especialidad) {

        String rutSiguientePaciente = simularLlamadaExternaWaitlist(especialidad);
        if (rutSiguientePaciente != null) {
            RegistroReasignacion registro = new RegistroReasignacion(idCitaCancelada, especialidad, "EXITOSA");
            registro.setRutPacienteReasignado(rutSiguientePaciente);
            return repository.save(registro);
        } else {
            return repository.save(new RegistroReasignacion(idCitaCancelada, especialidad, "SIN_PACIENTES"));
        }
    }

    public RegistroReasignacion fallbackBuscarSiguientePaciente(Long idCitaCancelada, String especialidad, Throwable ex) {
        System.err.println("El servicio de listas de espera falló. Guardando registro para reintentar luego.");
        return repository.save(new RegistroReasignacion(idCitaCancelada, especialidad, "FALLIDA_POR_COMUNICACION"));
    }

    private String simularLlamadaExternaWaitlist(String especialidad) {
        return "9876543-2"; 
    }
}
