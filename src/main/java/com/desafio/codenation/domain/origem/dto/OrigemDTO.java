package com.desafio.codenation.domain.origem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrigemDTO implements Serializable {
    private static final long serialVersionUUID = 1L;

    private Long id;
    private String nome;
    private LocalDate dtInscricao;
    private String descricao;

//    public OrigemDTO(Origem origem) {
//        this.id = origem.getId();
//        this.nome = origem.getNome();
//        this.dtInscricao = origem.getDtInscricao();
//        this.descricao = origem.getDescricao();
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public LocalDate getDtInscricao() {
//        return dtInscricao;
//    }
//
//    public void setDtInscricao(LocalDate dtInscricao) {
//        this.dtInscricao = dtInscricao;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
}

