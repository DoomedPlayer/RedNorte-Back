package com.RedNorte.SaludRedNorte.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "citas_medicas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rutPaciente; // Conexión lógica con Patient-Service

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private String estado; 

    private String boxAtencion; 

    public Cita() {}

    public Cita(String rutPaciente, LocalDateTime fechaHora, String especialidad, String boxAtencion) {
        this.rutPaciente = rutPaciente;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        this.estado = "PROGRAMADA";
        this.boxAtencion = boxAtencion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRutPaciente() { return rutPaciente; }
    public void setRutPaciente(String rutPaciente) { this.rutPaciente = rutPaciente; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getBoxAtencion() { return boxAtencion; }
    public void setBoxAtencion(String boxAtencion) { this.boxAtencion = boxAtencion; }
}