package com.desafio.codenation.domain.eventos;

import com.desafio.codenation.domain.eventos.enums.TypeLevel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Evento implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String log;

    private TypeLevel level;

    private String origem;

    private LocalDate data;

    private Integer quantidade;

    public Evento() {
    }

    public Evento(String descricao, String log, TypeLevel level, String origem, LocalDate data, Integer quantidade) {
        this.descricao = descricao;
        this.log = log;
        this.level = level;
        this.origem = origem;
        this.data = data;
        this.quantidade = quantidade;
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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public TypeLevel getLevel() {
        return level;
    }

    public void setLevel(TypeLevel level) {
        this.level = level;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        Evento eventos = (Evento) o;
        return id.equals(eventos.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
