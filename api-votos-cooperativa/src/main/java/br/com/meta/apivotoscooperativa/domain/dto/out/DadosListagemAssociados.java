package br.com.meta.apivotoscooperativa.domain.dto.out;

import br.com.meta.apivotoscooperativa.domain.entity.Associado;

public record DadosListagemAssociados(Long id, String nome, String cpf) {
    public DadosListagemAssociados(Associado associado){
        this(associado.getId(), associado.getNome(), associado.getCpf());
    }
}
