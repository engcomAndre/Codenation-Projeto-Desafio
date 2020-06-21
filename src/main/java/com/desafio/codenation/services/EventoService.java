package com.desafio.codenation.services;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.repositories.EventoRepositorie;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventoService {

    private final EventoRepositorie eventoRepositorie;

    public EventoService(EventoRepositorie eventoRepositorie) {
        this.eventoRepositorie = eventoRepositorie;
    }

    public Evento getEvento(Long id) {
        return eventoRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado.")
        );
    }

    public Page<Evento> getEventos(Predicate predicate, Pageable pageable) {
        Page<Evento> pageEventos = eventoRepositorie.findAll(predicate, pageable);
        if (pageEventos.isEmpty())
            throw new ObjectNotFoundException("Eventos não encontrados para os parametros informados.");
        return pageEventos;
    }

    public Evento insert(Evento evento) {
        return eventoRepositorie.save(evento);
    }

    public void updateEvento(Long id, Evento newEvento) {
        Evento evento = getEvento(id); //throw ObjectNotFoundException if event not found
        updtEvento(evento, newEvento);
        eventoRepositorie.save(evento);
    }

    public void deleteEvento(Long id) {
        getEvento(id); //throw ObjectNotFoundException if event not found
        eventoRepositorie.deleteById(id);
    }


    private void updtEvento(Evento evento, Evento updtEvento) {
        if (evento.getOrigem() != updtEvento.getOrigem()) {
            evento.setOrigem(updtEvento.getOrigem());
        }
        if (!Objects.equals(evento.getDescricao(), updtEvento.getDescricao())) {
            evento.setDescricao(updtEvento.getDescricao());
        }
        if (evento.getLevel() != updtEvento.getLevel()) {
            evento.setLevel(updtEvento.getLevel());
        }
        if (!Objects.equals(evento.getQuantidade(), updtEvento.getQuantidade())) {
            evento.setOrigem(updtEvento.getOrigem());
        }
        updtLog(evento.getLog(), updtEvento.getLog());
    }

    private void updtLog(Log log, Log updtLog) {
        if (!Objects.equals(log.getDescricao(), updtLog.getDescricao())) {
            log.setDescricao(updtLog.getDescricao());
        }
    }

}
