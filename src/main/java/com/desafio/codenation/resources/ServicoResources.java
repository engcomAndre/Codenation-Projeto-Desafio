package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.dto.NovoServicoDTO;
import com.desafio.codenation.domain.origem.dto.ServicoDTO;
import com.desafio.codenation.domain.origem.mapper.NovoServicoMapper;
import com.desafio.codenation.domain.origem.mapper.ServicoMapper;
import com.desafio.codenation.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("servico")
public class ServicoResources {

    private final ServicoService servicoService;

    private final ServicoMapper servicoMapper;

    private final NovoServicoMapper novoServicoMapper;

    @Autowired
    public ServicoResources(ServicoService servicoService, ServicoMapper servicoMapper,NovoServicoMapper novoServicoMapper) {
        this.servicoService = servicoService;
        this.servicoMapper = servicoMapper;
        this.novoServicoMapper = novoServicoMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(servicoMapper.map(servicoService.getServicoById(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ServicoDTO>> getServicos(Pageable pageable ) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        servicoService
                                .getServicos(pageable).stream()
                                .map(servicoMapper::map)
                                .collect(Collectors.toList())));


    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertService(@RequestBody NovoServicoDTO novoServicoDTO) {
        Servico servico = servicoService.insert(novoServicoMapper.map(novoServicoDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(servico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
