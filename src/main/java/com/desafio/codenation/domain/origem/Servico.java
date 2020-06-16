package com.desafio.codenation.domain.origem;

import com.desafio.codenation.domain.eventos.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
public class Servico extends Origem implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Builder(builderMethodName = "builder")
    public Servico(Long id, String nome, String descricao, String username, String password, List<Evento> eventos, LocalDate createdAt) {
        super(id, nome, descricao, username, password, eventos, createdAt);
    }

}