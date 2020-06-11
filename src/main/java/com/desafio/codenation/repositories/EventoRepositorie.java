package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EventoRepositorie extends JpaRepository<Evento, Long> {
}
