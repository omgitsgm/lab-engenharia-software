package br.com.laudai.domain.exception;

public class PacienteCpfDuplicadoException extends RuntimeException {

    public PacienteCpfDuplicadoException(String cpf) {
        super("Já existe um paciente cadastrado com o CPF " + cpf + ".");
    }

}
