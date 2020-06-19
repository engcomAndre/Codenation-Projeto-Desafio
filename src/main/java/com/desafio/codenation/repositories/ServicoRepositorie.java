package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.eventos.QEvento;
import com.desafio.codenation.domain.origem.QServico;
import com.desafio.codenation.domain.origem.Servico;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ServicoRepositorie extends BaseRepository<Servico, Long>, QuerydslPredicateExecutor<Servico>, QuerydslBinderCustomizer<QServico> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QServico queryServico) {
        bindings.bind(queryServico);
    }
}
