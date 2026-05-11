package com.RedNorte.SaludRedNorte.Factory;

public class CirugiaAtencion implements AtencionMedica {
    @Override
    public String procesarAtencion(String rutPaciente) {
        return "Reservando pabellón y equipo quirúrgico para el paciente: " + rutPaciente;
    }

    @Override
    public String obtenerNivelPrioridadBase() {
        return "ALTA";
    }
}
