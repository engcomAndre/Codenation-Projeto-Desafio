package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origin.Origins;

import java.util.Optional;

public interface OriginsRepositorie extends BaseRepository<Origins, Long> {

    Optional<Origins> findByIdAndOriginKeyAndActive(Long id, String originKey, Boolean active);

    Optional<Origins> findByOriginKeyAndActive(String originKey, Boolean active);

    Optional<Origins> findByOriginKey(String originKey);

}
