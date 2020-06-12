package com.desafio.codenation.domain.origem.DTO;

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
    private LocalDate createdAt;
    private String descricao;

}

