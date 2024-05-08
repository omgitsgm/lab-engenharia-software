package br.com.laudai.controller;

import br.com.laudai.controller.dto.ConsultaInput;
import br.com.laudai.controller.dto.ConsultaOutput;
import br.com.laudai.controller.util.ConsultaMapper;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente/{pacienteId}/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaMapper consultaMapper;

    public ConsultaController(ConsultaService consultaService, ConsultaMapper consultaMapper){
        this.consultaService = consultaService;
        this.consultaMapper = consultaMapper;
    }



    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Consulta agendada com sucesso.")
    public void agendar(@PathVariable Integer pacienteId, @RequestBody @Valid ConsultaInput consultaInput) {
        consultaService.agendar(pacienteId, consultaInput.exameId(), consultaInput.laboratorioId(),
                consultaInput.dataHorario());
    }

    @DeleteMapping("/{consultaId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Consulta cancelada com sucesso.")
    public void cancelar(@PathVariable Integer pacienteId, @PathVariable Integer consultaId) {
        consultaService.cancelar(pacienteId, consultaId);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaOutput>> visualizarConsultas(@PathVariable Integer pacienteId) {

        List<Consulta> consultas = consultaService.visualizarConsultas(pacienteId);

        List<ConsultaOutput> consultaOutputList = consultas.stream().map(consultaMapper::toConsultaOutput).toList();

        return ResponseEntity.ok(consultaOutputList);
    }

}
