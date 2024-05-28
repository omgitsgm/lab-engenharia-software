package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.AutenticacaoException;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.Radiologista;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.laudai.common.PacienteTestConstants.*;
import static br.com.laudai.common.RadiologistaTestConstants.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoServiceImpl autenticacaoService;

    @Mock
    private PacienteService pacienteService;

    @Mock
    private RadiologistaService radiologistaService;

    @Test
    void autentica_withValidCredentials_returnsPaciente(){

        when(pacienteService.findByEmailAndSenha(anyString(), anyString()))
                .thenReturn(PACIENTE_ENTITY);

        Paciente paciente = autenticacaoService.autentica(anyString(), anyString());

        assertThat(paciente).isEqualTo(PACIENTE_ENTITY);

    }

    @Test
    void autentica_withInvalidCredentials_throwsAutenticacaoException(){

        when(pacienteService.findByEmailAndSenha(anyString(), anyString()))
                .thenThrow(AutenticacaoException.class);

        assertThatExceptionOfType(AutenticacaoException.class)
                .isThrownBy(() -> autenticacaoService.autentica("nome@email.com", "12345678"));

    }

    @Test
    void autenticaRadiologista_withValidCredentials_returnsPaciente(){

        when(radiologistaService.autenticar(anyString(), anyString()))
                .thenReturn(RADIOLOGISTA_ENTITY);

        Radiologista radiologista = autenticacaoService.autenticaRadiologista(anyString(), anyString());

        assertThat(radiologista).isEqualTo(RADIOLOGISTA_ENTITY);

    }

    @Test
    void autenticaRadiologista_withInvalidCredentials_throwsAutenticacaoException(){

        when(radiologistaService.autenticar(anyString(), anyString()))
                .thenThrow(AutenticacaoException.class);

        assertThatExceptionOfType(AutenticacaoException.class)
                .isThrownBy(() -> autenticacaoService.autenticaRadiologista("1387173", "12345678"));

    }

}
