package br.com.laudai.domain.exception;

public class AcessoNegadoException extends RuntimeException {

    public AcessoNegadoException() {
        super("Acesso negado. Você não possui permissão para realizar essa operação.");
    }
}
