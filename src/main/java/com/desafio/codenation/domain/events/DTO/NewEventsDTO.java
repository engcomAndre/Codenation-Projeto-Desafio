package com.desafio.codenation.domain.events.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe que representa resposta de cadastro de evento bem sucedida.")
public class NewEventsDTO {
    @ApiModelProperty(notes = "Descrição do evento", example = "Descrição de um evento qualquer.", required = true)
    private String description;
    @ApiModelProperty(notes = "Tipo do evento.", example = "ERROR", required = true, position = 1)
    private String level;
    @ApiModelProperty(notes = "Identificador do Sistema ou Serviço que originou o evento.", example = "10", required = true, position = 2)
    private String originId;
    @ApiModelProperty(notes = "Chave do Sistema ou Serviço que originou o evento.", example = "193eae9b6f3741749f3b91d8a9fc49cc", required = true, position = 3)
    private String originKey;
    @ApiModelProperty(notes = "Quantidade de ocorrências do evento.", example = "4", required = true, position = 4)
    private Integer quantity;
    @ApiModelProperty(notes = "Descrição do log do evento", example = "Descrição de um log de evento 193eae9b6f3741749f3b91d8a9fc49cc", required = true, position = 5)
    private String logDescription;
}

