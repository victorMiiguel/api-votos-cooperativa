package br.com.meta.apivotoscooperativa.domain.service;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosCadastroAssociado;
import br.com.meta.apivotoscooperativa.domain.dto.out.DadosDetalhamentoAssociado;
import br.com.meta.apivotoscooperativa.domain.entity.Associado;
import br.com.meta.apivotoscooperativa.domain.repository.AssociadoRepository;
import br.com.meta.apivotoscooperativa.domain.repository.VotosRepository;
import br.com.meta.apivotoscooperativa.infra.Messages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {
    @Autowired
    public AssociadoService(AssociadoRepository repository,
                            VotosRepository votosRepository) {
        this.repository = repository;
        this.votosRepository = votosRepository;
    }


    private final AssociadoRepository repository;
    private final VotosRepository votosRepository;

    public DadosDetalhamentoAssociado cadastrar(DadosCadastroAssociado dados) {
        ValidaCpfService validaCpfService = new ValidaCpfService();
        if (!validaCpfService.validarCpf(dados.cpf().trim())) {
            throw new EntityNotFoundException(Messages.CPF_NAO_ELEGIVEL);
        }
        var associado = new Associado(dados);
        if (repository.existsByCpf(dados.cpf())) {
            throw new EntityNotFoundException(Messages.CPF_JA_CADASTRADO);
        }
        repository.save(associado);
        return new DadosDetalhamentoAssociado(associado);
    }

    public Page<DadosDetalhamentoAssociado> listar(Pageable pageable) {
        if (repository.findAll(pageable).map(DadosDetalhamentoAssociado::new).isEmpty()) {
            throw new EntityNotFoundException(Messages.ASSOCIADOS_INEXISTENTES);
        }
        return repository.findAll(pageable).map(DadosDetalhamentoAssociado::new);
    }

    public void deletar (Long id){
        var associadoOptional = repository.findById(id);
        if(associadoOptional.isEmpty()){
            throw new EntityNotFoundException(Messages.ASSOCIADO_INEXISTENTE);
        }
        var associado = associadoOptional.get();
        var votos = new DadosDetalhamentoAssociado(associado);
        repository.deleteById(associado.getId());
        votosRepository.deleteById(votos.id());
    }
}

