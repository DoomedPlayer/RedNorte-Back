package com.RedNorte.SaludRedNorte.Model;

import jakarta.persistence.*;;

@Entity
@Table(name = "pacientes_espera")
public class PacienteEspera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rut;
    private String especialidadRequerida;
    private String nivelPrioridad; 

    public PacienteEspera() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    
    public String getEspecialidad() { return especialidadRequerida; }
    public void setEspecialidad(String especialidad) { this.especialidadRequerida = especialidad; }
    
    public String getNivelPrioridad() { return nivelPrioridad; }
    public void setNivelPrioridad(String nivel) { this.nivelPrioridad = nivel; }
}