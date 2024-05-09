package br.com.laudai.web.mapper;

import br.com.laudai.web.dto.output.ConsultaOutput;
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
