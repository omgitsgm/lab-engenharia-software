package br.com.laudai.controller.util;

import br.com.laudai.controller.dto.ConsultaOutput;
import br.com.laudai.domain.model.Consulta;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    public ConsultaOutput toConsultaOutput(Consulta consulta) {

        return new ConsultaOutput(
                consulta.getPaciente().getNome(),
                consulta.getExame().getNome(),
                consulta.getLaboratorio().getNome(),
                consulta.getDataHorario()
        );

    }

}
