package br.com.laudai.domain.exception;

public class ModeloApiException extends RuntimeException {

    public ModeloApiException() {
        super("Ocorreu um erro ao estabelecer conexão com a API do Modelo.");
    }
}
