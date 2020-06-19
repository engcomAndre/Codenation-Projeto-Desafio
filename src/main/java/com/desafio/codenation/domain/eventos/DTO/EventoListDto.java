package com.desafio.codenation.domain.eventos.DTO;

import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoListDto {
    private Long id;
    private String descricao;
    private TypeLevel level;
}
