package com.desafio.codenation.domain.origin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewOriginDto {
    private String nome;
    private String descricao;
    private String chave;
    private String password;
}
