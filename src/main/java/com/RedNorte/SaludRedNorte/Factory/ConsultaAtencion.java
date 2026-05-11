package com.RedNorte.SaludRedNorte.Factory;

public class ConsultaAtencion implements AtencionMedica {
    @Override
    public String procesarAtencion(String rutPaciente) {
        return "Asignando box de atención para consulta médica al paciente: " + rutPaciente;
    }

    @Override
    public String obtenerNivelPrioridadBase() {
        return "BAJA"; // Las consultas generales suelen tener prioridad menor
    }
}



