package com.desafio.codenation.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Sistema implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dtInscricao;
    private String descricao;

    public Sistema() {
    }

    public Sistema(String nome, LocalDate dtInscricao, String descricao) {
        this.nome = nome;
        this.dtInscricao = dtInscricao;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtInscricao() {
        return dtInscricao;
    }

    public void setDtInscricao(LocalDate dtInscricao) {
        this.dtInscricao = dtInscricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sistema)) return false;
        Sistema sistema = (Sistema) o;
        return getId().equals(sistema.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
