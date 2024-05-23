package br.com.laudai.domain.exception;

public class RadiologistaCrmDuplicadoException extends RuntimeException {

    public RadiologistaCrmDuplicadoException(String crm) {
        super("Já existe um radiologista cadastrado com o CRM: " + crm + ".");
    }
}
