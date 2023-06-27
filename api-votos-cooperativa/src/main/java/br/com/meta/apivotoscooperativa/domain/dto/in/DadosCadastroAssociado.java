package br.com.meta.apivotoscooperativa.domain.dto.in;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAssociado (
    @NotBlank
    String nome,

    @NotBlank
    String cpf){

}

