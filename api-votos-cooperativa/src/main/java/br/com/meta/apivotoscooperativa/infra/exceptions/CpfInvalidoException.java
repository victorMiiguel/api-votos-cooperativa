package br.com.meta.apivotoscooperativa.infra.exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String msg) { super(msg); }
}
