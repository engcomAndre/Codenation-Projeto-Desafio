package com.desafio.codenation.domain.eventos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoEventoDTO {
    private String descricao;
    private String level;
    private String origemId;
    private String chave;
    private Integer quantidade;
    private String logDescricao;
}

