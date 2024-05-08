package br.com.laudai.controller.util;

import br.com.laudai.controller.dto.LaboratorioInput;
import br.com.laudai.controller.dto.LaboratorioOutput;
import br.com.laudai.domain.model.Laboratorio;
import org.springframework.stereotype.Component;

@Component
public class LaboratorioMapper {
    public Laboratorio toLaboratorio(LaboratorioInput laboratorioInput) {

        return new Laboratorio(
                laboratorioInput.nome()
        );

    }

    public LaboratorioOutput toLaboratorioOutput(Laboratorio laboratorio) {

        return new LaboratorioOutput(
                laboratorio.getNome()
        );

    }
}
