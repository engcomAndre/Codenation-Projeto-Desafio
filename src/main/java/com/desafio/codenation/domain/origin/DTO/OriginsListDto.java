package com.desafio.codenation.domain.origin.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OriginsListDto {
    private Long id;
    private String name;
    private String description;
}
