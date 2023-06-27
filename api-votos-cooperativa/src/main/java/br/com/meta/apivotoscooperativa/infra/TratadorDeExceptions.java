package br.com.meta.apivotoscooperativa.infra;

import br.com.meta.apivotoscooperativa.infra.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> error404(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors();
        var campoInvalido = errors.stream()
                .map(FieldError::getField).collect(Collectors.joining(", "));
        var msg = "O campo '" + campoInvalido + "' não pode ser vazio!";
        return ResponseEntity.badRequest().body(msg);
    }
    @ExceptionHandler(RequisicaoFalhaException.class)
    public ResponseEntity falhaNaRequisicao(RequisicaoFalhaException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(SemSessoesAtivasException.class)
    public ResponseEntity semSessaoAtiva(SemSessoesAtivasException e) {
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(VotoUnicoException.class)
    public ResponseEntity votoUnico(VotoUnicoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity cpfInvalido(CpfInvalidoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(SessaoFechadaException.class)
    public ResponseEntity sessaoFechadaException(SessaoFechadaException e) {
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(SintaxeVotoInvalidaException.class)
    public ResponseEntity sintaxeVotoInvalidaException(SintaxeVotoInvalidaException e){
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
    }
}
