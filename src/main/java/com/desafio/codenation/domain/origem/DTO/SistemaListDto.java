package com.desafio.codenation.domain.origem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SistemaListDto {
    private Long id;
    private String nome;
    private String descricao;
}
