package com.desafio.codenation.domain.origem;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Servico extends Origem {

    public Servico(String nome, LocalDate dtInscricao, String descricao, String identificador, String chave) {
        super(nome, dtInscricao, descricao, identificador, chave);
    }

    public Servico() {
    }
}
