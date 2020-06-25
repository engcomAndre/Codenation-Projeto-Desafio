package com.desafio.codenation.domain.origin;

import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
public class Services extends Origins implements Serializable {

    @Builder(builderMethodName = "builderServico")
    public Services(Long id, String nome, String descricao, String chave, String password, Boolean ativo, Set<TypeUser> perfis, List<User> users, List<Events> events, LocalDateTime createdAt) {
        super(id, nome, descricao, chave, password, ativo, perfis, users, events, createdAt);
    }
}