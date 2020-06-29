package com.desafio.codenation.resources;

import com.desafio.codenation.domain.events.DTO.EventsDTO;
import com.desafio.codenation.domain.events.DTO.EventsListDto;
import com.desafio.codenation.domain.events.DTO.NewEventsDTO;
import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.events.mapper.EventsMapper;
import com.desafio.codenation.resources.interfaces.EventsResourcesContract;
import com.desafio.codenation.services.EventsService;
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

@Api(tags = {"5 - Eventos"}, value = "Recursos de Eventos", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("evento")
public class EventsResources implements EventsResourcesContract {

    private final EventsService eventsService;

    private final EventsMapper eventsMapper;

    public EventsResources(EventsService eventsService, EventsMapper eventsMapper) {
        this.eventsService = eventsService;
        this.eventsMapper = eventsMapper;
    }


    public ResponseEntity<EventsDTO> getEventoById(Long id) {
        return ResponseEntity.ok().body(eventsMapper.map(eventsService.getEvento(id)));
    }

    public ResponseEntity<Page<EventsListDto>> getEventos(
            Predicate predicate,
            Long id,
            Long descricao,
            Long level,
            Long quantidade,
            String sort,
            String page,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        eventsService
                                .getEventos(predicate, pageable).stream()
                                .map(eventsMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    public ResponseEntity<Void> insertEvento(NewEventsDTO newEventsDTO) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventsService
                        .insertEvento(newEventsDTO).getId())
                .toUri()).build();
    }


    public ResponseEntity<Void> updateEvento(Long id, NewEventsDTO newEventsDTO) {
        Events events = eventsMapper.map(newEventsDTO);

//        events.setOrigins(originService.findById(Long.valueOf(newEventsDTO.getOrigemId())));
//
//        events.setLog(Log.builder()
//                .events(events)
//                .descricao(newEventsDTO.getLogDescricao())
//                .build());

        eventsService.updateEvento(id, events);

        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Void> deleteEvento(Long id) {
        eventsService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<List<TypeLevel>> getTypeLevelResponseEntity() {
        return ResponseEntity
                .ok()
                .body(Arrays.asList(TypeLevel.values()));
    }
}
