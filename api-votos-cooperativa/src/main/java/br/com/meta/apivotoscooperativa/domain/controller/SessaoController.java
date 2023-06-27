package br.com.meta.apivotoscooperativa.domain.controller;

import br.com.meta.apivotoscooperativa.domain.dto.in.DadosSessaoDTO;
import br.com.meta.apivotoscooperativa.domain.service.SessaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("sessao")
public class SessaoController {

    @Autowired
    public SessaoController(SessaoService sessaoService){
        this.sessaoService = sessaoService;}

    private final SessaoService sessaoService;


    @PostMapping
    public ResponseEntity abrirSessao(@RequestBody @Valid DadosSessaoDTO dados, UriComponentsBuilder uriBuilder) {
        var sessaoAberta = sessaoService.abrirSessao(dados);
        var uri = uriBuilder.path("/sessao/{id}").buildAndExpand(sessaoAberta.getId()).toUri();
        return ResponseEntity.created(uri).body(sessaoAberta);
    }

}
