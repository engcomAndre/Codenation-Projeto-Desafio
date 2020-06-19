package com.desafio.codenation.services;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.repositories.EventoRepositorie;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepositorie eventoRepositorie;

    public Evento getEvento(Long id) {
        return eventoRepositorie.findById(id).orElse(null);
    }

    public Page<Evento> getEventos(Predicate predicate,Pageable pageable) {
        return eventoRepositorie.findAll(predicate,pageable);
    }

    public Evento insert(Evento evento) {
        return eventoRepositorie.save(evento);
    }
}
