package com.desafio.codenation.domain.origem;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Sistema extends Origem implements Serializable {
    private static final long serialVersionUUID = 1L;

    public Sistema(String nome, LocalDate dtInscricao, String descricao, String identificador, String chave) {
        super(nome, dtInscricao, descricao, identificador, chave);
    }

    public Sistema() {
    }
}
