package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.security.SecurityEntity;

import java.util.Optional;

public interface SecurityRepositorie extends BaseRepository<SecurityEntity,Long> {

    Optional<SecurityEntity> findByUsername(String username);
}
