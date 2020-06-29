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
import com.desafio.codenation.services.utils.updtUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        updtUtils.updtEvento(events, newEvents);
        eventsRepositorie.save(events);
    }

    public void deleteEvento(Long id) {
        getEvento(id); //throw ObjectNotFoundException if event not found
        eventsRepositorie.deleteById(id);
    }

    public Events insertEvento(NewEventsDTO newEventsDTO) {

        Origins origins = originService.findByIdAndAndOriginKeyAndAtivo(
                Long.valueOf(newEventsDTO.getOriginId()),
                SecurityEntityService.authenticatedUsername());

        if (origins == null && !SecurityEntityService.hasGrant(TypeUser.ADMIN)) {
            throw new AuthorizationException("Acesso Negado ,Usuário ,sistema ou serviço não authenticado.");
        }

        origins = originService.findByIdAndAndOriginKeyAndAtivo(
                Long.valueOf(newEventsDTO.getOriginId()),
                newEventsDTO.getOriginKey());
        if (origins == null) {
            throw new ObjectNotFoundException("Origem não encontrado para os parâmetros informados");
        }

        Events events = eventsMapper.map(newEventsDTO);

        events.setOrigins(origins);

        events.setLog(Log.builder()
                .events(events)
                .description(newEventsDTO.getLogDescription())
                .build());

        events.setOrigins(origins);

        events.setLog(Log.builder()
                .events(events)
                .description(newEventsDTO.getLogDescription())
                .build());

        return eventsRepositorie.save(events);
    }

}
