package br.com.meta.apivotoscooperativa.domain.service;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosPautaDTO;
import br.com.meta.apivotoscooperativa.domain.dto.out.DadosDetalhamentoPautaDTO;
import br.com.meta.apivotoscooperativa.domain.entity.Pauta;
import br.com.meta.apivotoscooperativa.domain.repository.PautaRepository;
import br.com.meta.apivotoscooperativa.infra.Messages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }
    private final PautaRepository pautaRepository;

    public DadosDetalhamentoPautaDTO cadastrar(DadosPautaDTO dados) {
        var pauta = new Pauta();
        pauta.setTitulo(dados.getTitulo().trim());
        pauta.setDescricao(dados.getDescricao().trim());
        pauta.setVotosSim(0);
        pauta.setVotosNao(0);
        pautaRepository.save(pauta);
        return new DadosDetalhamentoPautaDTO(pauta);
    }
    public void deletar(Long id) {
        if (!pautaRepository.existsById(id)) {
            throw new EntityNotFoundException(Messages.PAUTA_INEXISTENTE);
        }
        pautaRepository.deleteById(id);
    }
    public Page<DadosDetalhamentoPautaDTO> listar(Pageable pageable) {
        if (pautaRepository.findAll(pageable).map(DadosDetalhamentoPautaDTO::new).isEmpty()) {
            throw new EntityNotFoundException(Messages.PAUTAS_INEXISTENTES);
        }
        return pautaRepository.findAll(pageable).map(DadosDetalhamentoPautaDTO::new);
    }
}
