package com.RedNorte.SaludRedNorte.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes_maestro")
public class Paciente {

    @Id
    @Column(name = "rut", unique = true, nullable = false)
    private String rut;
    @Column(nullable = false)
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String emailContacto;
    private String telefono;

    public Paciente() {}

    public Paciente(String rut,  String emailContacto) {
        this.rut = rut;
        this.emailContacto = emailContacto;
    }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getNombreCompleto() {return nombreCompleto;}
    public void setNombreCompleto(String nombre) {this.nombreCompleto = nombre;}

    public String getEmailContacto() { return emailContacto; }
    public void setEmailContacto(String emailContacto) { this.emailContacto = emailContacto; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}