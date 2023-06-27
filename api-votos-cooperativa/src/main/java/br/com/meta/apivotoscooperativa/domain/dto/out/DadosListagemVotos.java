package br.com.meta.apivotoscooperativa.domain.dto.out;

import br.com.meta.apivotoscooperativa.domain.entity.Votos;


public record DadosListagemVotos(Long numeroDaSessao,String titulo, String descricao, Long idAssociado, String voto ) {
    public DadosListagemVotos(Votos votos){
        this(votos.getSessao().getNumeroDaSessao(),
                votos.getSessao().getPauta().getTitulo(),
                votos.getSessao().getPauta().getDescricao(),
                votos.getAssociado().getId(),
                votos.getVoto());

    }

}
