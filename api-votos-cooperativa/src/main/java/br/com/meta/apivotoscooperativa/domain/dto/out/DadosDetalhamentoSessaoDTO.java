package br.com.meta.apivotoscooperativa.domain.dto.out;

import br.com.meta.apivotoscooperativa.domain.entity.Pauta;
import br.com.meta.apivotoscooperativa.domain.entity.Sessao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DadosDetalhamentoSessaoDTO {
    private Long id;
    private Pauta pauta;
    private Boolean ativa;
    private LocalDateTime abertaAte;

    public DadosDetalhamentoSessaoDTO(Sessao sessao) {
        this.id = sessao.getNumeroDaSessao();
        this.pauta = sessao.getPauta();
        this.ativa = sessao.getAtiva();
        this.abertaAte = sessao.getAbertaAte();
    }
}
