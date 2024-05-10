package br.com.laudai.domain.exception;

public class PacienteEmailDuplicadoException extends RuntimeException{

    public PacienteEmailDuplicadoException(String email){
        super("Já existe um paciente cadastrado com o email " + email + ".");
    }
}
