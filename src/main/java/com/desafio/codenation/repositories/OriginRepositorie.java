package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origem.Origem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OriginRepositorie extends JpaRepository<Origem, Long> {
    Optional<Origem> findByIdentificador(String identificador);
}
