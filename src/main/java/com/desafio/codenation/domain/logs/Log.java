package com.desafio.codenation.domain.logs;

import com.desafio.codenation.domain.eventos.Evento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Log implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public Log() {
    }

    public Log(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonIgnore
    public Evento getEvent() {
        return evento;
    }

    public void setEvent(Evento evento) {
        this.evento = evento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return getId().equals(log.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
