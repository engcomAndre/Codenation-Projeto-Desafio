package com.desafio.codenation.domain.origin.DTO;

import com.desafio.codenation.validation.OriginInsert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
@OriginInsert
@ApiModel(description = "Classe que representa resposta de cadastro de sistema ou serviço bem sucedida.")
public class NewOriginDto {

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @Length(min = 5, max = 60, message = "Campo deve possuir tamanho mínimo de 5 e máximo de 60 caracteres.")
    @ApiModelProperty(notes = "Nome do sistema ou serviço", example = "Nome do sistema/serviço", required = true)
    private String name;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @Length(min = 10, max = 250, message = "Campo deve possuir tamanho mínimo de 10 e máximo de 250 caracteres.")
    @ApiModelProperty(notes = "Descrição do sistema/serviço", example = "Descrição de um novo sistema/sistema", required = true)
    private String description;

    @Length(max = 120, message = "Campo deve possuir tamanho mínimo de 10 e máximo de 120 caracteres.")
    @ApiModelProperty(notes = "Chave única de username para acesso ao sistema.Caso não informada ,será gerada automaticamente.", example = "c161c8a94fd94711b863642c0ed09021")
    private String originKey;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @Length(min = 5, max = 20, message = "Campo deve possuir tamanho mínimo de 5 e máximo de 20 caracteres.")
    @ApiModelProperty(notes = "Senha de acesso ao sistema.", example = "123456", required = true)
    private String password;
}
