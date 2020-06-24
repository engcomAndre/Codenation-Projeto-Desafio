package com.desafio.codenation.domain.origem;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
public class Servico extends Origem implements Serializable {

    @Builder(builderMethodName = "builderServico")
    public Servico(Long id, String nome, String descricao, String chave, String password, Boolean ativo, Set<TypeUser> perfis, List<Evento> eventos, LocalDate createdAt) {
        super(id, nome, descricao, chave, password, ativo, perfis, eventos, createdAt);
    }
}