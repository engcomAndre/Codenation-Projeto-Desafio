package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.Evento;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorie extends BaseRepository<Evento, Long> {

}
