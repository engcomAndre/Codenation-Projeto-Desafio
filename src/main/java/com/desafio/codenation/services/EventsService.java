package com.desafio.codenation.services;

import com.desafio.codenation.domain.events.DTO.NewEventsDTO;
import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.mapper.EventsMapper;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.EventsRepositorie;
import com.desafio.codenation.services.exception.AuthorizationException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventsService {

    private final EventsRepositorie eventsRepositorie;

    private final OriginService originService;

    private final EventsMapper eventsMapper;

    public EventsService(EventsRepositorie eventsRepositorie, OriginService originService, EventsMapper eventsMapper) {

        this.eventsRepositorie = eventsRepositorie;
        this.originService = originService;
        this.eventsMapper = eventsMapper;

    }

    public Events getEvento(Long id) {
        return eventsRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado.")
        );
    }

    public Page<Events> getEventos(Predicate predicate, Pageable pageable) {
        Page<Events> pageEventos = eventsRepositorie.findAll(predicate, pageable);
        if (pageEventos.isEmpty())
            throw new ObjectNotFoundException("Eventos não encontrados para os parametros informados.");
        return pageEventos;
    }


    public void updateEvento(Long id, Events newEvents) {
        Events events = getEvento(id); //throw ObjectNotFoundException if event not found
        updtEvento(events, newEvents);
        eventsRepositorie.save(events);
    }

    public void deleteEvento(Long id) {
        getEvento(id); //throw ObjectNotFoundException if event not found
        eventsRepositorie.deleteById(id);
    }

    public Events insertEvento(NewEventsDTO newEventsDTO) {

        Origins origins = originService.findByIdAndAndChaveAndAtivo(
                Long.valueOf(newEventsDTO.getOrigemId()),
                SecurityEntityService.authenticatedUsername());

        if (origins == null && !SecurityEntityService.hasGrant(TypeUser.ADMIN)) {
            throw new AuthorizationException("Acesso Negado");
        }

        origins = originService.findByIdAndAndChaveAndAtivo(
                Long.valueOf(newEventsDTO.getOrigemId()),
                newEventsDTO.getChave());
        if (origins == null) {
            throw new ObjectNotFoundException("Origem não encontrado para os parâmetros informados");
        }

        Events events = eventsMapper.map(newEventsDTO);

        events.setOrigins(origins);

        events.setLog(Log.builder()
                .events(events)
                .descricao(newEventsDTO.getLogDescricao())
                .build());

        events.setOrigins(origins);

        events.setLog(Log.builder()
                .events(events)
                .descricao(newEventsDTO.getLogDescricao())
                .build());

        return eventsRepositorie.save(events);


    }


    private void updtEvento(Events events, Events updtEvents) {
        if (events.getOrigins() != updtEvents.getOrigins()) {
            events.setOrigins(updtEvents.getOrigins());
        }
        if (!Objects.equals(events.getDescricao(), updtEvents.getDescricao())) {
            events.setDescricao(updtEvents.getDescricao());
        }
        if (events.getLevel() != updtEvents.getLevel()) {
            events.setLevel(updtEvents.getLevel());
        }
        if (!Objects.equals(events.getQuantidade(), updtEvents.getQuantidade())) {
            events.setOrigins(updtEvents.getOrigins());
        }
        updtLog(events.getLog(), updtEvents.getLog());
    }

    private void updtLog(Log log, Log updtLog) {
        if (!Objects.equals(log.getDescricao(), updtLog.getDescricao())) {
            log.setDescricao(updtLog.getDescricao());
        }
    }


}
