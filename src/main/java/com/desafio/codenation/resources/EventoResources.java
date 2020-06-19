package com.desafio.codenation.resources;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.DTO.EventoListDto;
import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.eventos.mapper.EventoMapper;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.services.EventoService;
import com.desafio.codenation.services.OrigemService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("evento")
public class EventoResources {

    private final EventoService eventoService;

    private final OrigemService origemService;

    private final EventoMapper eventoMapper;

    public EventoResources(EventoService eventoService, EventoMapper eventoMapper, OrigemService origemService) {
        this.eventoService = eventoService;
        this.origemService = origemService;
        this.eventoMapper = eventoMapper;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventoMapper.map(eventoService.getEvento(id)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<EventoListDto>> getEventos(
            @QuerydslPredicate(root = Evento.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        eventoService
                                .getEventos(predicate, pageable).stream()
                                .map(eventoMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insertEvento(@RequestBody NovoEventoDTO novoEventoDTO) {

        Evento evento = eventoMapper.map(novoEventoDTO);

        evento.setOrigem(origemService.findById(Long.valueOf(novoEventoDTO.getOrigemId())));

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventoService
                        .insert(evento).getId())
                .toUri()).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvento(@PathVariable("id") Long id,@RequestBody NovoEventoDTO novoEventoDTO) {
        Evento evento = eventoMapper.map(novoEventoDTO);

        evento.setOrigem(origemService.findById(Long.valueOf(novoEventoDTO.getOrigemId())));

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        eventoService.updateEvento(id, evento);

        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable("id") Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/event-level")
    public ResponseEntity<List<TypeLevel>> getTypeLevelResponseEntity() {
        return ResponseEntity
                .ok()
                .body(Arrays.asList(TypeLevel.values()));
    }
}
