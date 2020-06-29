package com.desafio.codenation.domain.origin.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ApiModel(description = "Classe que representa resposta de cadastro de sistema ou serviço bem sucedida.")
public class NewOriginDto {
    @ApiModelProperty(notes = "Nome do sistema ou serviço", example = "Nome do sistema/serviço", required = true)
    private String name;
    @ApiModelProperty(notes = "Descrição do sistema/serviço", example = "Descrição de um novo sistema/sistema", required = true)
    private String description;
    @ApiModelProperty(notes = "Chave única de username para acesso ao sistema.Caso não informada ,será gerada automaticamente.", example = "c161c8a94fd94711b863642c0ed09021")
    private String originKey;
    @ApiModelProperty(notes = "Senha de acesso ao sistema.", example = "123456", required = true)
    private String password;
}
