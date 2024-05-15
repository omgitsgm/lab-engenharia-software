package br.com.laudai.domain.handler;

import br.com.laudai.domain.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class LaudaiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos.");
        problemDetail.setType(URI.create("/errors/campos-invalidos"));

        Map<String, String> campos = ex.getBindingResult().getAllErrors()
                .stream().collect(Collectors.toMap(
                        objectError -> ((FieldError)objectError).getField(),
                        objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("campos", campos);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(AcessoNegadoException.class)
    public ProblemDetail handleAcessoNegadoException(AcessoNegadoException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/acesso-negado"));
        return problemDetail;
    }

    @ExceptionHandler(ConsultaIndisponivelException.class)
    public ProblemDetail handleConsultaIndisponivelException(ConsultaIndisponivelException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/consulta-indisponivel"));
        return problemDetail;
    }

    @ExceptionHandler(ConsultaInexistenteException.class)
    public ProblemDetail handleConsultaInexistenteException(ConsultaInexistenteException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/consulta-inexistente"));
        return problemDetail;
    }

    @ExceptionHandler(ExameDuplicadoException.class)
    public ProblemDetail handleExameDuplicadoException(ExameDuplicadoException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/exame-duplicado"));
        return problemDetail;
    }

    @ExceptionHandler(ExameIndisponivelException.class)
    public ProblemDetail handleExameIndisponivelException(ExameIndisponivelException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/exame-indisponivel"));
        return problemDetail;
    }

    @ExceptionHandler(ExameInexistenteException.class)
    public ProblemDetail handleExameInexistenteException(ExameInexistenteException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/exame-inexistente"));
        return problemDetail;
    }

    @ExceptionHandler(LaboratorioInexistenteException.class)
    public ProblemDetail handleLaboratorioInexistenteException(LaboratorioInexistenteException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/laboratorio-inexistente"));
        return problemDetail;
    }

    @ExceptionHandler(PacienteInexistenteException.class)
    public ProblemDetail handlePacienteInexistenteException(PacienteInexistenteException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/paciente-inexistente"));
        return problemDetail;
    }

    @ExceptionHandler(PacienteCpfDuplicadoException.class)
    public ProblemDetail handlePacienteCpfDuplicadoException(PacienteCpfDuplicadoException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/paciente-cpf-duplicado"));
        return problemDetail;
    }

    @ExceptionHandler(PacienteEmailDuplicadoException.class)
    public ProblemDetail handlePacienteEmailDuplicadoException(PacienteEmailDuplicadoException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/paciente-email-duplicado"));
        return problemDetail;
    }

    @ExceptionHandler(AutenticacaoException.class)
    public ProblemDetail handleAutenticacaoException(AutenticacaoException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setType(URI.create("/errors/credencial-invalida"));
        return problemDetail;
    }

}
