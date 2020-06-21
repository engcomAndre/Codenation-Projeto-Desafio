package com.desafio.codenation.domain.origem;

import com.desafio.codenation.domain.eventos.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Entity
public class Servico extends Origem implements Serializable {

    @Builder
    public Servico(Long id, String nome, String descricao, String chave, Boolean ativo, List<Evento> eventos, LocalDate createdAt) {
        super(id, nome, descricao, chave, ativo, eventos, createdAt);
    }
}