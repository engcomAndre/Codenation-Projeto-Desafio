package com.desafio.codenation.domain.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Classe que representa resposta da autenticação bem sucedida.")
public class SecurityResponseSuccesfulDTO implements Serializable {
    private static final long serialVersionUUID = 1L;
    @ApiModelProperty(notes = "Mensagem com informações da ocorrência da autenticação.", example = "Authentication SUSCCESFUL.", required = true)
    private String message;

    @ApiModelProperty(notes = "Identificador do tipo de token retornado.", example = "Bearer", required = true, position = 1)
    private String tokenType;

    @ApiModelProperty(notes = "Token retornado do tipo informado.", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJleHAiOjE1OTM1MjQ3ODl9.uQE6Szzm91HhJUiau9nVjjE980gFeFs20n6S0Z_jhcXTJ9YpUZHyJiLLDxPOZIppGElm2mz3gnLoKZsCdq2EWA", required = true, position = 2)
    private String accessToken;
}
