package br.com.meta.apivotoscooperativa.infra.exceptions;

public class SessaoFechadaException extends RuntimeException {
    public SessaoFechadaException(String msg) { super(msg); }
}
