package br.com.laudai.domain.exception;

public class ExameIndisponivelException extends RuntimeException {

    public ExameIndisponivelException(String nomeExame, String nomeLaboratorio) {
        super("O exame " + nomeExame + " não está disponível no laboratório " + nomeLaboratorio + ".");
    }
}
