package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origem.Origem;

import java.util.Optional;

public interface OrigemRepositorie extends BaseRepository<Origem, Long> {

    Optional<Origem> findByIdAndChaveAndAtivo(Long id, String chave, Boolean ativo);

    Optional<Origem> findByChave(String chave);

}
