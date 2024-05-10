package br.com.laudai.domain.exception;

public class ConsultaInexistenteException extends RuntimeException {

    public ConsultaInexistenteException() {
        super("Não foi possível encontrar a consulta.");
    }
}
