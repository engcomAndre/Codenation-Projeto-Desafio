package com.desafio.codenation.domain.origem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NovoServicoDTO {

    private String nome;
    private LocalDate createdAt;
    private String descricao;

    private String identificador;
    private String chave;
}

