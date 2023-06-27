package br.com.meta.apivotoscooperativa.domain.dto.out;

import br.com.meta.apivotoscooperativa.domain.entity.Associado;


public record DadosDetalhamentoAssociado(Long id, String nome, String cpf) {

    public DadosDetalhamentoAssociado (Associado associado){

        this(associado.getId(), associado.getNome(), associado.getCpf());
    }
}
