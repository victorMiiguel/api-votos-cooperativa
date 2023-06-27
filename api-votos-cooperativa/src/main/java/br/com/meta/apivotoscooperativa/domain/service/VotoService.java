package br.com.meta.apivotoscooperativa.domain.service;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosVotosDTO;
import br.com.meta.apivotoscooperativa.domain.dto.out.DadosDetalhamentoAssociado;
import br.com.meta.apivotoscooperativa.domain.dto.out.DadosListagemVotos;
import br.com.meta.apivotoscooperativa.domain.entity.Votos;
import br.com.meta.apivotoscooperativa.domain.repository.AssociadoRepository;
import br.com.meta.apivotoscooperativa.domain.repository.SessaoRepository;
import br.com.meta.apivotoscooperativa.domain.repository.VotosRepository;
import br.com.meta.apivotoscooperativa.infra.Messages;
import br.com.meta.apivotoscooperativa.infra.exceptions.SessaoFechadaException;
import br.com.meta.apivotoscooperativa.infra.exceptions.SintaxeVotoInvalidaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class VotoService {
    @Autowired
    public VotoService(SessaoRepository sessaoRepository,
                       AssociadoRepository associadoRepository,
                       VotosRepository votosRepository,
                       ValidaCpfService validaCpfService){
        this.sessaoRepository = sessaoRepository;
        this.associadoRepository = associadoRepository;
        this.votosRepository = votosRepository;
        this.validaCpfService = validaCpfService;
    }
    private final SessaoRepository sessaoRepository;
    private final AssociadoRepository associadoRepository;
    private final VotosRepository votosRepository;
    private final ValidaCpfService validaCpfService;


    public DadosListagemVotos votar(DadosVotosDTO dados) {
        var agora = LocalDateTime.now();
        var numeroDaSessao = sessaoRepository.findById(dados.getNumeroDaSessao());
        var idAssociado = associadoRepository.findById(dados.getIdAssociado());
        if(idAssociado.isEmpty()){
            throw new EntityNotFoundException(Messages.ASSOCIADO_INEXISTENTE);
        }
        var associado = idAssociado.get();
        var associadoVotou = votosRepository.existsByAssociadoIdAndSessaoId(dados.getIdAssociado(), dados.getNumeroDaSessao());
        var voto = dados.getVoto();
        var cpfDoAssociado = new DadosDetalhamentoAssociado(associado);



        if (numeroDaSessao.isEmpty()) {
            throw new EntityNotFoundException(Messages.SESSAO_INEXISTENTE);
        }
        var sessao = numeroDaSessao.get();

        if (agora.isAfter(sessao.getAbertaAte())){
            throw new SessaoFechadaException(Messages.SESSAO_FECHADA);
        }
        if (!associadoRepository.existsByCpf(cpfDoAssociado.cpf())){
            throw new EntityNotFoundException(Messages.ASSOCIADO_INEXISTENTE);
        }

        if (associadoVotou >= 1) {
            throw new EntityNotFoundException(Messages.VOTO_UNICO);
        }

        if(voto.equalsIgnoreCase("sim")){
            votosRepository.atualizarVotosSim(dados.getNumeroDaSessao());
        } else if(voto.equalsIgnoreCase("nao") || voto.equalsIgnoreCase("não")){
            votosRepository.atualizarVotosNao(dados.getNumeroDaSessao());
        } else {
            throw new SintaxeVotoInvalidaException(Messages.SYNTAX_VOTO_INVALIDO);
        }




        Votos votos = new Votos();
        votos.setSessao(sessao);
        votos.setAssociado(associado);
        votos.setVoto(dados.getVoto());

        votosRepository.save(votos);
        return new DadosListagemVotos(votos);
    }

}
