package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.validation.UserInsert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@UserInsert
@ApiModel(description = "Classe que representa resposta de usuários.")
public class NewUserDTO {

    @Email(message = "Email inválido")
    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @Length(min = 5, max = 120, message = "Campo deve possuir tamanho mínimo de 5 e máximo de 60 caracteres.")
    @ApiModelProperty(notes = "Email do sistema ou serviço,identificador único.", example = "email@email.com", required = true)
    private String email;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @Length(min = 5, max = 20, message = "Campo deve possuir tamanho mínimo de 5 e máximo de 20 caracteres.")
    @ApiModelProperty(notes = "Senha de acesso ao sistema.", example = "123456", required = true)
    private String password;

    @NotEmpty(message = "Campo não pode ser vazio.")
    @NotNull(message = "Campo obrigatório.")
    @ApiModelProperty(notes = "Perfis de usuário de sistema.", required = true)
    private Set<TypeUser> grants;
}
