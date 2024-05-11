package br.com.laudai.web.mapper;

import br.com.laudai.domain.model.Exame;
import br.com.laudai.web.dto.output.ExameOutput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExameMapper {

    ExameOutput toExameOutput(Exame exame);

}
