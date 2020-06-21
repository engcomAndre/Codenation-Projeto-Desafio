package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.QEvento;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorie extends BaseRepository<Evento, Long>, QuerydslPredicateExecutor<Evento>, QuerydslBinderCustomizer<QEvento> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QEvento queryEvento) {
//        bindings.bind(queryEvento);
    }
}

