package com.desafio.codenation.resources;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("evento")
public class EventoResources {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getContacts(@PathVariable Long id) {
        Evento evento = eventoService.getEvento(id);
        return ResponseEntity.ok().body(evento);
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getUsers() {
        List<Evento> eventos = eventoService.getEventos();
        return ResponseEntity.ok().body(eventos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@RequestBody Evento evento) {
        Evento _evento = eventoService.insert(evento);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(_evento.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
