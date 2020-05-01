package com.desafio.codenation.services;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.repositories.EventoRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepositorie eventoRepositorie;

    public Evento getEvento(Long id) {
        return eventoRepositorie.findById(id).orElse(null);
    }

    public List<Evento> getEventos() {
        return eventoRepositorie.findAll();
    }


    public Evento insert(Evento evento) {
        return eventoRepositorie.save(evento);
    }
}
