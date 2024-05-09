package br.com.laudai.web.mapper;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.web.dto.input.PacienteInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring", imports = ArrayList.class)
public interface PacienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consultas", expression = "java(new ArrayList<>())")
    Paciente toPaciente(PacienteInput pacienteInput);


}
