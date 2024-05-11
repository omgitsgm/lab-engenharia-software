package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;

import java.time.LocalDateTime;

public interface ConsultaService {

    Consulta agendar(Integer pacienteId, Integer exameId, Integer laboratorioId, LocalDateTime dataHorario);

    void cancelar(Integer pacienteId, Integer consultaId);

    Consulta findById(Integer id);

}
