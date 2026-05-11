package com.RedNorte.SaludRedNorte.Factory;

import org.springframework.stereotype.Component;

@Component
public class AtencionFactory {
    public AtencionMedica crearAtencion(String tipoAtencion) {
        if (tipoAtencion == null || tipoAtencion.isEmpty()) {
            throw new IllegalArgumentException("El tipo de atención no puede estar vacío");
        }

        switch (tipoAtencion.toUpperCase()) {
            case "CONSULTA":
                return new ConsultaAtencion();
            case "CIRUGIA":
                return new CirugiaAtencion();
            case "URGENCIA":
                return new UrgenciaAtencion();
            default:
                throw new IllegalArgumentException("Tipo de atención médica no soportado: " + tipoAtencion);
        }
    }
}
