package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.QEvents;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepositorie extends BaseRepository<Events, Long>, QuerydslPredicateExecutor<Events>, QuerydslBinderCustomizer<QEvents> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QEvents queryEvents) {
//        bindings.bind(queryEvents);
    }
}

