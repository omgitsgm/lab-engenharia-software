package br.com.laudai.domain.exception;

public class AutenticacaoException extends RuntimeException{

    public AutenticacaoException() {
        super("Acesso negado. Credenciais incorretas.");
    }
}
