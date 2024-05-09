package br.com.laudai.web.mapper;

import br.com.laudai.web.dto.input.LaboratorioInput;
import br.com.laudai.web.dto.output.LaboratorioOutput;
import br.com.laudai.domain.model.Laboratorio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Mapper(componentModel = "spring", imports = ArrayList.class)
public interface LaboratorioMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consultas", expression = "java(new ArrayList<>())")
    @Mapping(target = "exames", expression = "java(new ArrayList<>())")
    Laboratorio toLaboratorio(LaboratorioInput laboratorioInput);

    LaboratorioOutput toLaboratorioOutput(Laboratorio laboratorio);
}
