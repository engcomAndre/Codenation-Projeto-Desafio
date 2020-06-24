package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.logs.QLog;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepositorie extends BaseRepository<Log, Long>, QuerydslPredicateExecutor<Log>, QuerydslBinderCustomizer<QLog> {
    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QLog queryLog) {
        bindings.bind(queryLog);
    }
}
