package com.desafio.codenation.domain.events.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe que representa resposta de cadastro de evento bem sucedida.")
public class NewEventsDTO {

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @Length(min = 10, max = 250, message = "Descrição possui tamanho mínimo de 10 e máximo de 250 caracteres.")
    @ApiModelProperty(notes = "Descrição do evento", example = "Descrição de um evento qualquer.", required = true)
    private String description;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Tipo do evento.", example = "ERROR", required = true, position = 1)
    private String level;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Identificador do Sistema ou Serviço que originou o evento.", example = "10", required = true, position = 2)
    private String originId;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Chave do Sistema ou Serviço que originou o evento.", example = "193eae9b6f3741749f3b91d8a9fc49cc", required = true, position = 3)
    @Column(unique = true)
    private String originKey;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Quantidade de ocorrências do evento.", example = "4", required = true, position = 4)
    private Integer quantity;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Descrição do log do evento", example = "Descrição de um log de evento 193eae9b6f3741749f3b91d8a9fc49cc", required = true, position = 5)
    private String logDescription;
}

