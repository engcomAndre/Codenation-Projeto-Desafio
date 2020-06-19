package com.desafio.codenation.domain.origem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NovoServicoDTO {
    private String nome;
    private String descricao;
    private String chave;
}

