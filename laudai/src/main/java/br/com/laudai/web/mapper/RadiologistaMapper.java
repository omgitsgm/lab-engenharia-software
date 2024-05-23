package br.com.laudai.web.mapper;

import br.com.laudai.domain.model.Radiologista;
import br.com.laudai.web.dto.input.RadiologistaInput;
import br.com.laudai.web.dto.output.RadiologistaOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring", imports = ArrayList.class)
public interface RadiologistaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consultas", expression = "java(new ArrayList<>())")
    @Mapping(target = "laboratorio", ignore = true)
    Radiologista toRadiologista(RadiologistaInput radiologistaInput);


    @Mapping(target = "laboratorioId", expression = "java(radiologista.getLaboratorio().getId())")
    RadiologistaOutput toRadiologistaOutput(Radiologista radiologista);

}
