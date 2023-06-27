package br.com.meta.apivotoscooperativa.domain.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DadosSessaoDTO {
    @NotNull
    private Long idPauta;

    private Long duracaoEmMinutos;
}
