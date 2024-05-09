package br.com.laudai.web.mapper;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.web.dto.output.ConsultaOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring", imports = ArrayList.class)
public interface ConsultaMapper {

    @Mapping(target = "nomePaciente", source = "paciente.nome")
    @Mapping(target = "nomeExame", source = "exame.nome")
    @Mapping(target = "nomeLaboratorio", source = "laboratorio.nome")
    ConsultaOutput toConsultaOutput(Consulta consulta);

}
