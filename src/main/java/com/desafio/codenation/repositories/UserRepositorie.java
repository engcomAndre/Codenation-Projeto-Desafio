package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.QEvento;
import com.desafio.codenation.domain.logs.QLog;
import com.desafio.codenation.domain.user.QUser;
import com.desafio.codenation.domain.user.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositorie extends BaseRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

    Optional<User> findByEmail(String email);

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QUser queryUser) {
        bindings.bind(queryUser);
    }

}
