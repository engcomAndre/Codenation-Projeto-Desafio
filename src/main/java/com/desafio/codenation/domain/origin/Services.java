package com.desafio.codenation.domain.origin;

import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
public class Services extends Origins implements Serializable {

    @Builder(builderMethodName = "builderServico")

    public Services(Long id, @NotNull(message = "Nome é um campo obrigatório.") @Length(min = 5, max = 120, message = "Nome possui tamanho mínimo de 5 e máximo de 120 caracteres.") String name, @NotNull(message = "Descrição é um campo obrigatório.") @Length(min = 10, max = 250, message = "Descrição possui tamanho mínimo de 10 e máximo de 250 caracteres.") String description, @NotNull(message = "Chave é um campo obrigatório.") @Length(min = 5, max = 60, message = "Descrição possui tamanho mínimo de 10 e máximo de 250 caracteres.") String key, @NotEmpty(message = "Uma senha valida deve ser informada.") @Length(min = 5, max = 20, message = "Senha possui tamanho mínimo de 5 e máximo de 20 caracteres.") String password, Boolean active, @NotEmpty(message = "Pelo menos umm perfil de usuário deve ser informado.") Set<TypeUser> grants, List<User> users, List<Events> events, LocalDateTime createdAt) {
        super(id, name, description, key, password, active, grants, users, events, createdAt);
    }
}