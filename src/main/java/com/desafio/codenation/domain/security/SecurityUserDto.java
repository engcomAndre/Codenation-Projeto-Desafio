package com.desafio.codenation.domain.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Classe que representa os atributos de login de usuários.")
public class SecurityUserDto {

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Identificador único de acesso dos usuários.", example = "admin@admin.com", required = true)
    private String username;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Identificador único de acesso dos usuários.", example = "@admin", required = true, position = 1)
    private String password;
}
