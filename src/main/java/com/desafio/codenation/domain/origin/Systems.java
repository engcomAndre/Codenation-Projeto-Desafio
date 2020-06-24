package com.desafio.codenation.domain.origin;


import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
public class Systems extends Origins implements Serializable {

    @Builder(builderMethodName = "builderSistema")
    public Systems(Long id, String nome, String descricao, String chave, String password, Boolean ativo, Set<TypeUser> perfis, List<Events> events, LocalDate createdAt) {
        super(id, nome, descricao, chave, password, ativo, perfis, events, createdAt);
    }
}
