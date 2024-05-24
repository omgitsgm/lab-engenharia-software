package br.com.laudai.web.mapper;

import br.com.laudai.domain.model.ImagemExame;
import br.com.laudai.web.dto.output.ImagemExameOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImagemExameMapper {

    @Mapping(target = "resultadoExameId", expression = "java(imagemExame.getResultadoExame().getId())")
    @Mapping(target = "consultaId", expression = "java(imagemExame.getResultadoExame().getConsulta().getId())")
    ImagemExameOutput toImagemExameOutput(ImagemExame imagemExame);


}
