package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.Sistema;
import com.desafio.codenation.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepositorie extends JpaRepository<Sistema, Integer> {
}
