package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.service.PacienteService;
import br.com.laudai.web.dto.input.PacienteInput;
import br.com.laudai.web.dto.output.ConsultaOutput;
import br.com.laudai.web.dto.output.PacienteOutput;
import br.com.laudai.web.http.ResponseBody;
import br.com.laudai.web.mapper.ConsultaMapper;
import br.com.laudai.web.mapper.PacienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;
    private final ConsultaMapper consultaMapper;
    private static final String PACIENTE_URI = "/paciente/";

    @PostMapping
    public ResponseEntity<ResponseBody> save(@RequestBody @Valid PacienteInput pacienteInput) {

        Paciente paciente = pacienteService.save(pacienteMapper.toPaciente(pacienteInput));
        PacienteOutput pacienteOutput = pacienteMapper.toPacienteOutput(paciente);

        URI uri = URI.create(PACIENTE_URI + pacienteOutput.id());
        ResponseBody responseBody = new ResponseBody(
                HttpStatus.CREATED.value(),
                "Paciente cadastrado com sucesso.",
                new ArrayList<>(List.of(pacienteOutput))
        );

        return ResponseEntity.created(uri).body(responseBody);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteOutput> findById(@PathVariable Integer id) {

        Paciente paciente = pacienteService.findById(id);
        PacienteOutput pacienteOutput = pacienteMapper.toPacienteOutput(paciente);

        return ResponseEntity.ok(pacienteOutput);

    }

    @GetMapping("/{id}/consulta")
    public ResponseEntity<List<ConsultaOutput>> getConsultas(@PathVariable Integer id) {

        List<Consulta> consultas = pacienteService.getConsultas(id);

        List<ConsultaOutput> consultaOutputList = consultas.stream().map(consultaMapper::toConsultaOutput).toList();

        return ResponseEntity.ok(consultaOutputList);
    }

}
