package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.service.ConsultaService;
import br.com.laudai.web.dto.input.ConsultaInput;
import br.com.laudai.web.dto.output.ConsultaOutput;
import br.com.laudai.web.http.ResponseBody;
import br.com.laudai.web.mapper.ConsultaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente/{pacienteId}/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaMapper consultaMapper;

    @PostMapping
    public ResponseEntity<ResponseBody> agendar(@PathVariable Integer pacienteId,
                                                @RequestBody @Valid ConsultaInput consultaInput) {

        log.info("Inicializando agendamento de consulta.");

        Consulta consulta = consultaService.agendar(pacienteId, consultaInput.exameId(), consultaInput.laboratorioId(),
                consultaInput.dataHorario());

        ConsultaOutput consultaOutput = consultaMapper.toConsultaOutput(consulta);

        log.info("Montando resposta da requisição de agendamento.");
        URI uri = URI.create("/paciente/" + pacienteId + "/consulta/" + consultaOutput.id());
        ResponseBody responseBody = new ResponseBody(
                HttpStatus.CREATED.value(),
                "Consulta agendada com sucesso.",
                new ArrayList<>(List.of(consultaOutput))
        );

        log.info("Finalizando agendamento de consulta.");
        return ResponseEntity.created(uri).body(responseBody);

    }

    @DeleteMapping("/{consultaId}")
    public ResponseEntity<ResponseBody> cancelar(@PathVariable Integer pacienteId, @PathVariable Integer consultaId) {

        log.info("Inicializando cancelamento de consulta.");
        consultaService.cancelar(pacienteId, consultaId);

        log.info("Montando resposta da requisição de cancelamento.");
        ResponseBody responseBody = new ResponseBody(
                HttpStatus.OK.value(),
                "Consulta cancelada com sucesso.",
                new ArrayList<>(List.of(URI.create("/paciente/" + pacienteId + "/consulta")))
        );

        log.info("Finalizando cancelamento de consulta.");
        return ResponseEntity.ok(responseBody);
    }

}
