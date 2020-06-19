package com.desafio.codenation.domain.eventos.DTO;

import com.desafio.codenation.domain.logs.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Level;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoEventoDTO {
    private String descricao;
    private String level;
    private String origemId;
    private Integer quantidade;
    private String logDescricao;
}

