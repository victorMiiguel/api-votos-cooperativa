package br.com.meta.apivotoscooperativa.infra.exceptions;

public class SintaxeVotoInvalidaException extends RuntimeException {
    public SintaxeVotoInvalidaException(String msg) {
        super(msg);
    }
}
