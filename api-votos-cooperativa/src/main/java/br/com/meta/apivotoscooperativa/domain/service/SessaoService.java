package br.com.meta.apivotoscooperativa.domain.service;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosSessaoDTO;
import br.com.meta.apivotoscooperativa.domain.dto.out.DadosDetalhamentoSessaoDTO;
import br.com.meta.apivotoscooperativa.domain.entity.Sessao;
import br.com.meta.apivotoscooperativa.domain.repository.PautaRepository;
import br.com.meta.apivotoscooperativa.domain.repository.SessaoRepository;
import br.com.meta.apivotoscooperativa.infra.Messages;
import br.com.meta.apivotoscooperativa.infra.exceptions.RequisicaoFalhaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {
    @Autowired
    public SessaoService(SessaoRepository sessaoRepository,
                         PautaRepository pautaRepository){
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;}

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    public DadosDetalhamentoSessaoDTO abrirSessao(DadosSessaoDTO dados) {
        var fimDaVotacao = LocalDateTime.now().plusMinutes(1);
        var possivelPauta = pautaRepository.findById(dados.getIdPauta());
        if (possivelPauta.isEmpty()) {
            throw new EntityNotFoundException(Messages.PAUTA_INEXISTENTE);
        }
        if (dados.getDuracaoEmMinutos() != null) {
            fimDaVotacao = LocalDateTime.now().plusMinutes(dados.getDuracaoEmMinutos());
        }
        var pauta = possivelPauta.get();
        var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setAtiva(true);
        if (sessaoRepository.existsByPautaId(dados.getIdPauta())) {
                throw new RequisicaoFalhaException(Messages.EXISTE_SESSAO_PARA_PAUTA);
            }
        sessao.setAbertaAte(fimDaVotacao);
        sessaoRepository.save(sessao);
        return new DadosDetalhamentoSessaoDTO(sessao);
    }
}





