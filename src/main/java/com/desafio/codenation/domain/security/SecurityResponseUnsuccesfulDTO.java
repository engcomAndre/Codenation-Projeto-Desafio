package com.desafio.codenation.domain.security;

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
public class SecurityResponseUnsuccesfulDTO implements Serializable {
    private static final long serialVersionUUID = 1L;

    @ApiModelProperty(notes = "Mensagem com informações da ocorrência incorreta da autenticação.", example = "Authentication UNSUCCESFUL", required = true,position = 0)
    private String message;

}
