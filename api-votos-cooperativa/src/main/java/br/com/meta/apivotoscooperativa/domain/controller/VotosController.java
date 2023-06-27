package br.com.meta.apivotoscooperativa.domain.controller;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosVotosDTO;
import br.com.meta.apivotoscooperativa.domain.service.VotoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("votos")
public class VotosController {
    @Autowired
    public VotosController(VotoService votoService){
        this.votoService = votoService;}

    private final VotoService votoService;

    @PostMapping
    @Transactional
    public ResponseEntity abrirVotacao(@RequestBody @Valid DadosVotosDTO dados, UriComponentsBuilder uriBuilder){
        var votacaoIniciada = votoService.votar(dados);
        var uri = uriBuilder.path("/votos/{id}").buildAndExpand(votacaoIniciada).toUri();
        return ResponseEntity.ok(votacaoIniciada);
    }
}
