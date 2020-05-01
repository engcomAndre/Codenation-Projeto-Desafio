package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorie extends JpaRepository<Evento, Long> {
}
