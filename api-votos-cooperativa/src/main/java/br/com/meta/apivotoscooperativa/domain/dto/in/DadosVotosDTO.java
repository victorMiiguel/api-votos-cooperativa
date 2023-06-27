package br.com.meta.apivotoscooperativa.domain.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DadosVotosDTO{

        @NotNull
        Long numeroDaSessao;

        @NotNull
        Long idAssociado;

        @NotBlank
        String voto;

        public String getVoto(){
                return voto.toLowerCase();
        }
}

