package com.desafio.codenation.domain.origin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewOriginDto {
    private String name;
    private String description;
    private String key;
    private String password;
}
