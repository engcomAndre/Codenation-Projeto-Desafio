package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OriginRepositorie extends JpaRepository<Origin, Long> {
    Optional<Origin> findByIdentificador(String identificador);
}
