package br.com.meta.apivotoscooperativa.domain.controller;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosPautaDTO;
import br.com.meta.apivotoscooperativa.domain.dto.out.DadosDetalhamentoPautaDTO;
import br.com.meta.apivotoscooperativa.domain.repository.PautaRepository;
import br.com.meta.apivotoscooperativa.domain.service.PautaService;
import br.com.meta.apivotoscooperativa.infra.Messages;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pautas")
public class PautaController {
    @Autowired
    public PautaController(PautaRepository repository,
                           PautaService pautaService){
        this.repository = repository;
        this.pautaService = pautaService;

    }
    private final PautaRepository repository;
    private final PautaService pautaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosPautaDTO dados, UriComponentsBuilder uriBuilder) {
        var pautaCadastrada = pautaService.cadastrar(dados);
        var uri = uriBuilder.path("/pautas/{id}").buildAndExpand(pautaCadastrada.getId()).toUri();
        return ResponseEntity.created(uri).body(pautaCadastrada);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DadosDetalhamentoPautaDTO>> listar(@PageableDefault(size = 5, sort = {"id"})Pageable pageable) {
        var page = pautaService.listar(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable  Long id) {
        var idpauta = repository.getReferenceById(id);
        var dados = new DadosDetalhamentoPautaDTO(idpauta);

        return ResponseEntity.ok(new DadosDetalhamentoPautaDTO(idpauta));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        pautaService.deletar(id);
        return ResponseEntity.ok(Messages.PAUTA_DELETADA);
    }
}
