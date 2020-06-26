package com.desafio.codenation.domain.events.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEventsDTO {
    private String description;
    private String level;
    private String originId;
    private String key;
    private Integer quantity;
    private String logDescription;
}

