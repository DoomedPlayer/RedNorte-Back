package com.RedNorte.SaludRedNorte.Messaging;

import com.RedNorte.SaludRedNorte.Config.RabbitMQConfig;
import com.RedNorte.SaludRedNorte.Model.PacienteEspera;
import com.RedNorte.SaludRedNorte.Model.RegistroReasignacion;
import com.RedNorte.SaludRedNorte.Repository.PacienteEsperaRepository;
import com.RedNorte.SaludRedNorte.Repository.ReasignacionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ReasignacionConsumer {

    private final PacienteEsperaRepository pacienteRepository;
    private final ReasignacionRepository reasignacionRepository;

    public ReasignacionConsumer(PacienteEsperaRepository pacienteRepository, ReasignacionRepository reasignacionRepository) {
        this.pacienteRepository = pacienteRepository;
        this.reasignacionRepository = reasignacionRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_REASIGNACION)
    public void procesarMensaje(String mensaje) {
        String[] partes = mensaje.split(",");
        Long idCita = Long.parseLong(partes[0]);
        String especialidad = partes[1];
        List<PacienteEspera> lista = pacienteRepository.findByEspecialidadRequerida(especialidad);

        if (!lista.isEmpty()) {
            PacienteEspera primerPaciente = lista.get(0);

            RegistroReasignacion registro = new RegistroReasignacion(idCita, especialidad, "EXITOSA");
            registro.setRutPacienteReasignado(primerPaciente.getRut()); 
            reasignacionRepository.save(registro);
            pacienteRepository.delete(primerPaciente);
            
            System.out.println("Reasignación exitosa para paciente: " + primerPaciente.getRut());
        } else {
            reasignacionRepository.save(new RegistroReasignacion(idCita, especialidad, "SIN_PACIENTES"));
        }
    }
}
