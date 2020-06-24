package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origin.Origins;

import java.util.Optional;

public interface OriginsRepositorie extends BaseRepository<Origins, Long> {

    Optional<Origins> findByIdAndChaveAndAtivo(Long id, String chave, Boolean ativo);

    Optional<Origins> findByChaveAndAtivo(String chave, Boolean ativo);

}
