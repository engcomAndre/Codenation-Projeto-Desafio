package com.desafio.codenation.domain.origem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {
    private Long id;
    private String nome;
    private LocalDate createdAt;
    private String descricao;
}
