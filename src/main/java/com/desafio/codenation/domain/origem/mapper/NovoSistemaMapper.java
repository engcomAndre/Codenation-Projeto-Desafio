package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.DTO.NovoSistemaDTO;
import com.desafio.codenation.domain.origem.Sistema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NovoSistemaMapper {

    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
    })
    Sistema map(NovoSistemaDTO novoServicoDTO);
}



