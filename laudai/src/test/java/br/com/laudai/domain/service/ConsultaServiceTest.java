package br.com.laudai.domain.service;

import br.com.laudai.common.ConsultaTestConstants;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.infra.repository.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static br.com.laudai.common.ConsultaTestConstants.CONSULTA_ENTITY_WITHOUT_RESULTADO;
import static br.com.laudai.common.ConsultaTestConstants.LOCAL_DATE_TIME;
import static br.com.laudai.common.ExameTestConstants.EXAME_ENTITY;
import static br.com.laudai.common.LaboratorioTestConstants.LABORATORIO_ENTITY;
import static br.com.laudai.common.PacienteTestConstants.PACIENTE_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    private ConsultaServiceImpl consultaService;

    @Mock
    private PacienteService pacienteService;

    @Mock
    private ExameService exameService;

    @Mock
    private LaboratorioService laboratorioService;

    @Mock
    private ConsultaRepository consultaRepository;


    @Test
    void agendar_withValidData_returnsConsulta() {

        when(pacienteService.findById(anyInt())).thenReturn(PACIENTE_ENTITY);
        when(exameService.findById(anyInt())).thenReturn(EXAME_ENTITY);
        when(laboratorioService.findById(anyInt())).thenReturn(LABORATORIO_ENTITY);

        Consulta consultaMock = new Consulta(PACIENTE_ENTITY, EXAME_ENTITY, LABORATORIO_ENTITY, ConsultaTestConstants.LOCAL_DATE_TIME);

        when(consultaRepository.save(consultaMock)).thenReturn(CONSULTA_ENTITY_WITHOUT_RESULTADO);

        Consulta consulta = consultaService.agendar(anyInt(), anyInt(), anyInt(), any(LocalDateTime.class));

        assertThat(consulta).isNotNull();


    }



}
