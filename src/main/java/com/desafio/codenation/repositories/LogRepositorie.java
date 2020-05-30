package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.logs.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositorie extends JpaRepository<Log, Long> {
}
