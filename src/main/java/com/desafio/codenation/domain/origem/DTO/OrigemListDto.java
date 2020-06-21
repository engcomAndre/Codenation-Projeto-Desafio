package com.desafio.codenation.domain.origem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrigemListDto {
    private Long id;
    private String nome;
    private String descricao;
}
