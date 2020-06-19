package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.QEvento;
import com.desafio.codenation.domain.origem.QSistema;
import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.domain.user.QUser;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepositorie extends BaseRepository<Sistema, Long> , QuerydslPredicateExecutor<Sistema>, QuerydslBinderCustomizer<QSistema> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QSistema querySistema) {
        bindings.bind(querySistema);
    }


}
