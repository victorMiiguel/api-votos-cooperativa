package br.com.meta.apivotoscooperativa.domain.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DadosPautaDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

}
