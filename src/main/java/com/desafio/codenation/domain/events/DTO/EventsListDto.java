package com.desafio.codenation.domain.events.DTO;

import com.desafio.codenation.domain.events.enums.TypeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventsListDto {
    private Long id;
    private String description;
    private TypeLevel level;
}
