package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.user.enums.TypeUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe que representa resposta de usuários.")
public class NewUserDTO {
    @ApiModelProperty(notes = "Email do sistema ou serviço,identificador único.", example = "email@email.com", required = true)
    private String email;

    @ApiModelProperty(notes = "Senha de acesso ao sistema.", example = "123456", required = true)
    private String password;

    @ApiModelProperty(notes = "Perfis de usuário de sistema.", example = "[ADMIN,COMMOM_USER]", required = true)
    private Set<TypeUser> grants;
}
