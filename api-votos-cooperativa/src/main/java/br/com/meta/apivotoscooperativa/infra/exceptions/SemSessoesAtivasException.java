package br.com.meta.apivotoscooperativa.infra.exceptions;

public class SemSessoesAtivasException extends RuntimeException {
    public SemSessoesAtivasException(String msg) { super(msg); }
}
