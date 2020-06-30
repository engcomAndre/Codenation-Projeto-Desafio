package com.desafio.codenation.validation;

import com.desafio.codenation.domain.origin.DTO.NewOriginDto;
import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.repositories.OriginsRepositorie;
import com.desafio.codenation.resources.exception.FieldMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class OriginInsertValidator implements ConstraintValidator<OriginInsert, NewOriginDto> {

    private final OriginsRepositorie originsRepositorie;

    public OriginInsertValidator(OriginsRepositorie originsRepositorie) {
        this.originsRepositorie = originsRepositorie;
    }

    @Override
    public void initialize(OriginInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(NewOriginDto newOriginDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (originsRepositorie.findByOriginKey(newOriginDto.getOriginKey()).orElse(null) != null) {
            list.add(new FieldMessage("originKey", "Já esta sendo utilizada. "));
        }

        if(newOriginDto.getOriginKey().length() < 6 && !newOriginDto.getOriginKey().isEmpty()){
            list.add(new FieldMessage("originKey", "Campo quando informado ,deve ter no mínimo 5 caracteres. "));
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
