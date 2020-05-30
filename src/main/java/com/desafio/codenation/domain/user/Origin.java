package com.desafio.codenation.domain.user;

import com.desafio.codenation.domain.eventos.Evento;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Origin implements UserDetails {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dtInscricao;
    private String descricao;

    public String getIdentificador() {
        return identificador;
    }

    private String identificador;
    private String chave;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Evento> eventos;

    public Origin(String nome, LocalDate dtInscricao, String descricao, String identificador, String chave) {
        this.nome = nome;
        this.dtInscricao = dtInscricao;
        this.descricao = descricao;
        this.eventos = new ArrayList<>();

        this.identificador = identificador;
        this.chave = chave;
    }

    public Origin() {
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.identificador;
    }

    @Override
    public String getUsername() {
        return this.identificador;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
