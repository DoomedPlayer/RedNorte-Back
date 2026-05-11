package com.RedNorte.SaludRedNorte.Factory;

public class UrgenciaAtencion implements AtencionMedica {
    @Override
    public String procesarAtencion(String rutPaciente) {
        return "Activando protocolo de emergencia inmediata para el paciente: " + rutPaciente;
    }

    @Override
    public String obtenerNivelPrioridadBase() {
        return "EXTREMA";
    }
}
