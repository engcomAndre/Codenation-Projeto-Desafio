package com.desafio.codenation.validation;

import com.desafio.codenation.domain.user.DTO.NovoUserDTO;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.UserRepositorie;
import com.desafio.codenation.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsert, NovoUserDTO> {

    @Autowired
    private UserRepositorie userRepositorie;


    @Override
    public void initialize(UserInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(NovoUserDTO novoUserDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (novoUserDTO.getPerfis().isEmpty()) {
            list.add(new FieldMessage("perfis", "É necessário informar ao menos um perfil de usuário."));
        }
        if (novoUserDTO.getPerfis().contains(TypeUser.ADMIN) || novoUserDTO.getPerfis().contains(TypeUser.COMMON_USER)) {
            list.add(new FieldMessage("perfis", "Usuário já possui algum dos perfis informados. "));
        }
        if (userRepositorie.findByEmail(novoUserDTO.getEmail()).orElse(null) != null) {
            list.add(new FieldMessage("email", "Email já cadastrado."));
        }

        try {
            for (TypeUser t : novoUserDTO.getPerfis()) {
                if (!Arrays.asList(TypeUser.values()).contains(t)) {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            list.add(new FieldMessage("perfis", "Valores invaĺidos para perfis de usuário."));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
