package com.RedNorte.SaludRedNorte.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RedNorte.SaludRedNorte.Model.RegistroReasignacion;
import com.RedNorte.SaludRedNorte.Service.ReasignacionService;

@RestController
@RequestMapping("/api/reasignacion")
public class ReasignacionController {

    private final ReasignacionService service;

    @Autowired
    public ReasignacionController(ReasignacionService service) {
        this.service = service;
    }

    // Se envían los datos básicos de la cita que acaba de ser cancelada
    @PostMapping("/ejecutar")
    public ResponseEntity<RegistroReasignacion> ejecutarReasignacion(
            @RequestParam Long idCitaCancelada,
            @RequestParam String especialidad) {
        
        RegistroReasignacion resultado = service.procesarCancelacion(idCitaCancelada, especialidad);
        return ResponseEntity.ok(resultado);
    }
}
