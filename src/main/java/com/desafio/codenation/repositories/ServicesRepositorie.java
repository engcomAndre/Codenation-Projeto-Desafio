package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origin.QServices;
import com.desafio.codenation.domain.origin.Services;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ServicesRepositorie extends BaseRepository<Services, Long>, QuerydslPredicateExecutor<Services>, QuerydslBinderCustomizer<QServices> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QServices queryServices) {
        bindings.bind(queryServices);
    }
}
