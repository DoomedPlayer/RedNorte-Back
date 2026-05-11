package com.RedNorte.SaludRedNorte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RedNorte.SaludRedNorte.Model.PacienteEspera;
import com.RedNorte.SaludRedNorte.Service.ListaEsperaService;

@RestController
@RequestMapping("/api/espera")
public class ListaEsperaController {

    private final ListaEsperaService service;

    @Autowired
    public ListaEsperaController(ListaEsperaService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteEspera> registrarPaciente(@RequestBody PacienteEspera paciente, @RequestParam String tipoAtencion) {
        PacienteEspera nuevoPaciente = service.registrarPacienteEnEspera(paciente,tipoAtencion);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PacienteEspera>> obtenerLista() {
        return ResponseEntity.ok(service.obtenerTodosLosPacientes());
    }
}