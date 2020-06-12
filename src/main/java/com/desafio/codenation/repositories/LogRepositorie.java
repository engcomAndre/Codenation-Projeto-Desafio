package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.logs.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositorie extends BaseRepository<Log, Long> {
}
