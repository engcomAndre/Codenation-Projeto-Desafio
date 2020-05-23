package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginRepositorie extends JpaRepository<Origin, Long> {
}
