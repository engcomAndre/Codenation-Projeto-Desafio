package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origem.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepositorie extends JpaRepository<Sistema, Integer> {
}
