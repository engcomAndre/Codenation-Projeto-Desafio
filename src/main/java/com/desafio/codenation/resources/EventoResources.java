package com.desafio.codenation.resources;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EventoResources(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventoService.getEvento(id));
    }

    @GetMapping
    public ResponseEntity<Page<EventoDTO>> getEventos(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        eventoService
                                .getEventos(pageable)
                                .stream()
                                .map(EventoDTO::new).collect(Collectors.toList())));
    }

    @PostMapping
    public ResponseEntity<Void> insertEvento(@RequestBody Evento evento) {
        Evento _evento = eventoService.insert(evento);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(_evento.getId())
                .toUri()).build();
    }

}
