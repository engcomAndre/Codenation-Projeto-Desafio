package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.DTO.NovoServicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NovoServicoMapper {

    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
    })
    Servico map(NovoServicoDTO novoServicoDTO);
}



