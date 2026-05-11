package com.RedNorte.SaludRedNorte.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RedNorte.SaludRedNorte.Model.Paciente;
import com.RedNorte.SaludRedNorte.Service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    @Autowired
    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping("/registro")
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        Paciente guardado = service.registrarOActualizarPaciente(paciente);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Paciente> obtenerPaciente(@PathVariable String rut) {
        Paciente paciente = service.obtenerPacientePorRut(rut);
        return ResponseEntity.ok(paciente);
    }
}
