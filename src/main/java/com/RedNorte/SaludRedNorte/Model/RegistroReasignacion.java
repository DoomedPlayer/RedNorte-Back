package com.RedNorte.SaludRedNorte.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "registro_reasignaciones")
public class RegistroReasignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idCitaCancelada;

    private String rutPacienteReasignado;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private LocalDateTime fechaEjecucion;

    @Column(nullable = false)
    private String estadoReasignacion;

    public RegistroReasignacion() {}

    public RegistroReasignacion(Long idCitaCancelada, String especialidad, String estadoReasignacion) {
        this.idCitaCancelada = idCitaCancelada;
        this.especialidad = especialidad;
        this.fechaEjecucion = LocalDateTime.now();
        this.estadoReasignacion = estadoReasignacion;
    }

    public Long getIdCitaCancelada() { return idCitaCancelada; }
    public void setIdCitaCancelada(Long IdCancelada) { this.idCitaCancelada = IdCancelada; }

    public String getRutPacienteReasignado() {return rutPacienteReasignado;} 
    public void setRutPacienteReasignado(String rutPacienteReasignado) {
        this.rutPacienteReasignado = rutPacienteReasignado;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public LocalDateTime getFechaEjecucion() { return fechaEjecucion; }
    public void setFechaEjecuciond(LocalDateTime fecha) { this.fechaEjecucion = fecha; }

    public String getEstadoReasignacion() { return estadoReasignacion; }
    public void setEstadoReasignacion(String estado) { this.estadoReasignacion = estado; }    
}
