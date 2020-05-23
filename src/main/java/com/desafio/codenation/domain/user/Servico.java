package com.desafio.codenation.domain.user;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Servico extends Origin {

    public Servico(String nome, LocalDate dtInscricao, String descricao) {
        super(nome, dtInscricao, descricao);
    }

    public Servico(){
        super();
    }

}
