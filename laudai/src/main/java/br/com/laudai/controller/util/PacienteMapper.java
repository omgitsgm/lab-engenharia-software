package br.com.laudai.controller.util;

import br.com.laudai.controller.dto.PacienteInput;
import br.com.laudai.domain.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    public Paciente toPaciente(PacienteInput pacienteInput) {
        return new Paciente(
                pacienteInput.nome(),
                pacienteInput.cpf(),
                pacienteInput.email(),
                pacienteInput.telefone()
        );
    }

}
