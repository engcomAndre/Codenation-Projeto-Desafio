package com.desafio.codenation.services;

import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.mapper.EventoMapper;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.EventoRepositorie;
import com.desafio.codenation.services.exception.AuthorizationException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventoService {

    private final EventoRepositorie eventoRepositorie;

    private final OrigemService origemService;

    private final EventoMapper eventoMapper;

    public EventoService(EventoRepositorie eventoRepositorie, OrigemService origemService, EventoMapper eventoMapper) {

        this.eventoRepositorie = eventoRepositorie;
        this.origemService = origemService;
        this.eventoMapper = eventoMapper;

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


    public void updateEvento(Long id, Evento newEvento) {
        Evento evento = getEvento(id); //throw ObjectNotFoundException if event not found
        updtEvento(evento, newEvento);
        eventoRepositorie.save(evento);
    }

    public void deleteEvento(Long id) {
        getEvento(id); //throw ObjectNotFoundException if event not found
        eventoRepositorie.deleteById(id);
    }

    public Evento insertEvento(NovoEventoDTO novoEventoDTO) {

        Origem origem = origemService.findByIdAndAndChaveAndAtivo(
                Long.valueOf(novoEventoDTO.getOrigemId()),
                SecurityEntityService.authenticatedUsername());

        if (origem == null && !SecurityEntityService.hasGrant(TypeUser.ADMIN)) {
            throw new AuthorizationException("Acesso Negado");
        }

        origem = origemService.findByIdAndAndChaveAndAtivo(
                Long.valueOf(novoEventoDTO.getOrigemId()),
                novoEventoDTO.getChave());
        if (origem == null) {
            throw new ObjectNotFoundException("Origem não encontrado para os parâmetros informados");
        }

        Evento evento = eventoMapper.map(novoEventoDTO);

        evento.setOrigem(origem);

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        evento.setOrigem(origem);

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        return eventoRepositorie.save(evento);


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
