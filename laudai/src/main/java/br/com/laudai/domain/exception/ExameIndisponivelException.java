package br.com.laudai.domain.exception;

public class ExameIndisponivelException extends RuntimeException {

    public ExameIndisponivelException() {
        super("Exame indisponível. Este exame não está disponível neste laboratório.");
    }
}
