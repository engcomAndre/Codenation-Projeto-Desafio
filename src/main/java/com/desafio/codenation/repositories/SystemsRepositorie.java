package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.origin.QSystems;
import com.desafio.codenation.domain.origin.Systems;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemsRepositorie extends BaseRepository<Systems, Long>, QuerydslPredicateExecutor<Systems>, QuerydslBinderCustomizer<QSystems> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QSystems queryQSystems) {
        bindings.bind(queryQSystems);
    }


}
