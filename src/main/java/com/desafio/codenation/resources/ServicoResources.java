package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("servico")
public class ServicoResources {

    @Autowired
    private ServicoService servicoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Servico> getServicoById(@PathVariable Long id) {
        Servico servico = servicoService.getServicoById(id);
        return ResponseEntity.ok().body(servico);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Servico>> getServicos() {
        List<Servico> servicos = servicoService.getServicos();
        return ResponseEntity.ok().body(servicos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@RequestBody Servico _servico) {
        Servico servico = servicoService.insert(_servico);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(servico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
