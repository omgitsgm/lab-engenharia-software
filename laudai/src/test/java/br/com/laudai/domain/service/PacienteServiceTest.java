package br.com.laudai.domain.service;

import br.com.laudai.common.PacienteTestConstants;
import br.com.laudai.domain.exception.PacienteCpfDuplicadoException;
import br.com.laudai.domain.exception.PacienteEmailDuplicadoException;
import br.com.laudai.domain.exception.PacienteInexistenteException;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.infra.repository.PacienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.laudai.common.PacienteTestConstants.*;
import static br.com.laudai.common.PacienteTestConstants.PACIENTE_ENTITY;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    @Test
    void save_withValidData_returnsPaciente() {

        when(pacienteRepository.existsByCpf(anyString())).thenReturn(Boolean.FALSE);
        when(pacienteRepository.existsByEmail(anyString())).thenReturn(Boolean.FALSE);
        when(pacienteRepository.save(PACIENTE)).thenReturn(PACIENTE_ENTITY);

        Paciente paciente = pacienteService.save(PACIENTE);

        Assertions.assertThat(paciente).isEqualTo(PACIENTE_ENTITY);

    }

    @Test
    void save_withInvalidCpf_throwsCpfDuplicadoException() {
        when(pacienteRepository.existsByCpf(anyString())).thenReturn(Boolean.TRUE);

        Assertions.assertThatExceptionOfType(PacienteCpfDuplicadoException.class)
                .isThrownBy(() -> pacienteService.save(PACIENTE));

    }

    @Test
    void save_withInvalidCpf_throwsEmailDuplicadoException() {
        when(pacienteRepository.existsByCpf(anyString())).thenReturn(Boolean.FALSE);
        when(pacienteRepository.existsByEmail(anyString())).thenReturn(Boolean.TRUE);

        Assertions.assertThatExceptionOfType(PacienteEmailDuplicadoException.class)
                .isThrownBy(() -> pacienteService.save(PACIENTE));

    }

    @Test
    void findById_withExistingPaciente_returnsPaciente() {
        when(pacienteRepository.findById(anyInt())).thenReturn(Optional.of(PACIENTE_ENTITY));

        Paciente paciente = pacienteService.findById(anyInt());

        Assertions.assertThat(paciente).isEqualTo(PACIENTE_ENTITY);

    }

    @Test
    void findById_withUnexistingPaciente_throwsPacienteInexistenteException() {

        when(pacienteRepository.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(PacienteInexistenteException.class)
                .isThrownBy(() -> pacienteService.findById(1));

    }

}
