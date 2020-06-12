package com.desafio.codenation.domain.origem;


import com.desafio.codenation.domain.eventos.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
public class Sistema extends Origem implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Builder(builderMethodName = "builder")
    public Sistema(Long id, String nome, String descricao, String identificador, String chave, List<Evento> eventos, LocalDate createdAt) {
        super(id, nome, descricao, identificador, chave, eventos, createdAt);
    }

}
