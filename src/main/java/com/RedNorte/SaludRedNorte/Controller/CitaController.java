package com.RedNorte.SaludRedNorte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RedNorte.SaludRedNorte.Model.Cita;
import com.RedNorte.SaludRedNorte.Service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService service;

    @Autowired
    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping("/agendar")
    public ResponseEntity<Cita> agendarCita(@RequestBody Cita cita) {
        Cita nuevaCita = service.agendarCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Cita> cancelarCita(@PathVariable Long id) {
        Cita citaCancelada = service.cancelarCita(id);
        return ResponseEntity.ok(citaCancelada);
    }

    @GetMapping("/paciente/{rut}")
    public ResponseEntity<List<Cita>> obtenerCitasDePaciente(@PathVariable String rut) {
        return ResponseEntity.ok(service.obtenerCitasPorPaciente(rut));
    }
}
