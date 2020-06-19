package com.desafio.codenation.services;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.repositories.EventoRepositorie;
import com.desafio.codenation.repositories.OrigemRepositorie;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepositorie eventoRepositorie;

    @Autowired
    private OrigemRepositorie origemRepositorie;

    public Evento getEvento(Long id) {
        return eventoRepositorie.findById(id).orElse(null);
    }

    public Page<Evento> getEventos(Predicate predicate, Pageable pageable) {
        return eventoRepositorie.findAll(predicate, pageable);
    }

    public Evento insert(Evento evento) {
        return eventoRepositorie.save(evento);
    }

    public Evento updateEvento(Long id, Evento newEvento) {
        Evento evento = getEvento(id);
        updtEvento(evento, newEvento);
        return eventoRepositorie.save(evento);
    }

    public void deleteEvento(Long id) {
        getEvento(id);
        eventoRepositorie.deleteById(id);
    }


    public void updtEvento(Evento evento, Evento updtEvento) {
        if (evento.getOrigem() != updtEvento.getOrigem()) {
            evento.setOrigem(updtEvento.getOrigem());
        }
        if (evento.getDescricao() != updtEvento.getDescricao()) {
            evento.setDescricao(updtEvento.getDescricao());
        }
        if (evento.getLevel() != updtEvento.getLevel()) {
            evento.setLevel(updtEvento.getLevel());
        }
        if (evento.getQuantidade() != updtEvento.getQuantidade()) {
            evento.setOrigem(updtEvento.getOrigem());
        }
        updtLog(evento.getLog(),updtEvento.getLog());
    }

    public void updtLog(Log log, Log updtLog) {
        if(log.getDescricao() != updtLog.getDescricao()){
            log.setDescricao(updtLog.getDescricao());
        }

    }

}
