package com.desafio.codenation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class OriginDto {
    private String nome;
    private LocalDate dtInscricao;
    private String descricao;
}

