package com.desafio.codenation.resources;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.DTO.EventoListDto;
import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.eventos.mapper.EventoMapper;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.resources.interfaces.EventoResourcesContract;
import com.desafio.codenation.services.EventoService;
import com.desafio.codenation.services.OrigemService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Eventos"}, value = "Recursos de Eventos", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("evento")
public class EventoResources implements EventoResourcesContract {

    private final EventoService eventoService;

    private final OrigemService origemService;

    private final EventoMapper eventoMapper;

    public EventoResources(EventoService eventoService, EventoMapper eventoMapper, OrigemService origemService) {
        this.eventoService = eventoService;
        this.origemService = origemService;
        this.eventoMapper = eventoMapper;
    }


    public ResponseEntity<EventoDTO> getEventoById(Long id) {
        return ResponseEntity.ok().body(eventoMapper.map(eventoService.getEvento(id)));
    }

    public ResponseEntity<Page<EventoListDto>> getEventos(
            Predicate predicate,
            Long id,
            Long descricao,
            Long level,
            Long quantidade,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        eventoService
                                .getEventos(predicate, pageable).stream()
                                .map(eventoMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    public ResponseEntity<Void> insertEvento(NovoEventoDTO novoEventoDTO) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventoService
                        .insertEvento(novoEventoDTO).getId())
                .toUri()).build();
    }


    public ResponseEntity<Void> updateEvento(Long id, NovoEventoDTO novoEventoDTO) {
        Evento evento = eventoMapper.map(novoEventoDTO);

        evento.setOrigem(origemService.findById(Long.valueOf(novoEventoDTO.getOrigemId())));

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        eventoService.updateEvento(id, evento);

        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Void> deleteEvento(Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<List<TypeLevel>> getTypeLevelResponseEntity() {
        return ResponseEntity
                .ok()
                .body(Arrays.asList(TypeLevel.values()));
    }
}
