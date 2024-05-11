package br.com.laudai.web.controller;

import br.com.laudai.web.dto.input.ConsultaInput;
import br.com.laudai.web.dto.output.ConsultaOutput;
import br.com.laudai.web.http.ResponseBody;
import br.com.laudai.web.mapper.ConsultaMapper;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/paciente/{pacienteId}/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaMapper consultaMapper;

    @PostMapping
    public ResponseEntity<ResponseBody> agendar(@PathVariable Integer pacienteId,
                                                @RequestBody @Valid ConsultaInput consultaInput) {
        Consulta consulta = consultaService.agendar(pacienteId, consultaInput.exameId(), consultaInput.laboratorioId(),
                consultaInput.dataHorario());

        ConsultaOutput consultaOutput = consultaMapper.toConsultaOutput(consulta);

        URI uri = URI.create("/paciente/" + pacienteId + "/consulta/" + consultaOutput.id());
        ResponseBody responseBody = new ResponseBody(
                HttpStatus.CREATED.value(),
                "Consulta agendada com sucesso.",
                new ArrayList<>(List.of(consultaOutput))
        );

        return ResponseEntity.created(uri).body(responseBody);

    }

    @DeleteMapping("/{consultaId}")
    public ResponseEntity<ResponseBody> cancelar(@PathVariable Integer pacienteId, @PathVariable Integer consultaId) {
        consultaService.cancelar(pacienteId, consultaId);

        ResponseBody responseBody = new ResponseBody(
                HttpStatus.OK.value(),
                "Consulta cancelada com sucesso.",
                new ArrayList<>(List.of(URI.create("/paciente/" + pacienteId + "/consulta")))
        );

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaOutput>> visualizarConsultas(@PathVariable Integer pacienteId) {

        List<Consulta> consultas = consultaService.visualizarConsultas(pacienteId);

        List<ConsultaOutput> consultaOutputList = consultas.stream().map(consultaMapper::toConsultaOutput).toList();

        return ResponseEntity.ok(consultaOutputList);
    }

}
