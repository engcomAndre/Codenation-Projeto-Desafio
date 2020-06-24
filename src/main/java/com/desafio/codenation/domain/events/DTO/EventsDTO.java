package com.desafio.codenation.domain.events.DTO;

import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO {

    private Long id;
    private String descricao;
    private TypeLevel level;
    private Log log;
    private LocalDateTime createdAt;
    private Integer quantidade;
    private LocalDateTime modifiedData;

}
