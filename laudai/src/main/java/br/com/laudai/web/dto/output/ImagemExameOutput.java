package br.com.laudai.web.dto.output;

public record ImagemExameOutput(

        String nomeArquivo,
        String contentType,
        Long tamanho,
        Integer resultadoExameId,
        Integer consultaId

) { }
