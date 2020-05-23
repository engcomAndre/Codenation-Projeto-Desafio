package com.desafio.codenation.domain.user;

import com.desafio.codenation.domain.eventos.Evento;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Origin {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dtInscricao;
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Evento> eventos;

    public Origin(String nome, LocalDate dtInscricao, String descricao) {
        this.nome = nome;
        this.dtInscricao = dtInscricao;
        this.descricao = descricao;
        this.eventos = new ArrayList<>();
    }

    public Origin(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void addEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public void addEventos(List<Evento> eventos) {
        this.eventos.addAll(eventos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Origin)) return false;
        Origin origin = (Origin) o;
        return getId().equals(origin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
