package br.com.meta.apivotoscooperativa.domain.dto.out;

import br.com.meta.apivotoscooperativa.domain.entity.Pauta;
import lombok.Data;

@Data
public class DadosDetalhamentoPautaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private int votosSim;
    private int votosNao;
    public DadosDetalhamentoPautaDTO(Pauta pauta) {
        this.id = pauta.getId();
        this.titulo = pauta.getTitulo();
        this.descricao = pauta.getDescricao();
        this.votosSim = pauta.getVotosSim();
        this.votosNao = pauta.getVotosNao();

    }
}
