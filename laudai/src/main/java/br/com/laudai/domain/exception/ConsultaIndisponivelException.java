package br.com.laudai.domain.exception;

public class ConsultaIndisponivelException extends RuntimeException {

    public ConsultaIndisponivelException() {
        super("Consulta indiponível. Não é possível agendar uma consulta nesse laboratório nesta data e horário.");
    }
}
