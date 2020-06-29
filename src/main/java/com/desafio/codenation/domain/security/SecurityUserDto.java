package com.desafio.codenation.domain.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Classe que representa os atributos de login de usuários.")
public class SecurityUserDto {
    @ApiModelProperty(notes = "Identificador único de acesso dos usuários.", example = "admin@admin.com", required = true)
    private String username;
    @ApiModelProperty(notes = "Identificador único de acesso dos usuários.", example = "@admin", required = true, position = 1)
    private String password;
}
