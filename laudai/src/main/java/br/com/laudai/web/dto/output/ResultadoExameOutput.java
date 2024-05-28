package br.com.laudai.web.dto.output;

public record ResultadoExameOutput(

        Integer idConsulta,
        Integer idResultado,

        String nomeExame,
        String nomeLaboratorio,

        String nomePaciente,

        Double probabilidade

) {
}
