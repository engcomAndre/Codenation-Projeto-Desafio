package com.desafio.codenation.resources;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.mapper.EventoMapper;
import com.desafio.codenation.domain.eventos.mapper.NovoEventoMapper;
import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.services.EventoService;
import com.desafio.codenation.services.SecurityEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("evento")
public class EventoResources {

    private final EventoService eventoService;

    private final EventoMapper eventoMapper;

    private final NovoEventoMapper novoEventoMapper;

    public EventoResources(EventoService eventoService, EventoMapper eventoMapper, NovoEventoMapper novoEventoMapper) {
        this.eventoService = eventoService;
        this.eventoMapper = eventoMapper;
        this.novoEventoMapper = novoEventoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventoMapper.map(eventoService.getEvento(id)));
    }

    @GetMapping
    public ResponseEntity<Page<EventoDTO>> getEventos(Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        eventoService
                                .getEventos(pageable).stream()
                                .map(eventoMapper::map)
                                .collect(Collectors.toList())));
    }

    @PostMapping
    public ResponseEntity<Void> insertEvento(@RequestBody NovoEventoDTO novoEventoDTO) {
        Evento evento = eventoService.insert(novoEventoMapper.map(novoEventoDTO));
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(evento.getId())
                .toUri()).build();
    }

}
