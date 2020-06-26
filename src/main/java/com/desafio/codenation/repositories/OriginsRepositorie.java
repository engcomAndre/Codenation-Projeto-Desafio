package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origin.Origins;

import java.util.Optional;

public interface OriginsRepositorie extends BaseRepository<Origins, Long> {

    Optional<Origins> findByIdAndKeyAndActive(Long id, String key, Boolean active);

    Optional<Origins> findByKeyAndActive(String key, Boolean active);

}
