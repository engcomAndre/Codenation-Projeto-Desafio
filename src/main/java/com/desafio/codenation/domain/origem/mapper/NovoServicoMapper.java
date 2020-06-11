package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.dto.NovoServicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NovoServicoMapper {

    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "identificador", target = "identificador"),
            @Mapping(source = "chave", target = "chave"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),

    })
    Servico map(NovoServicoDTO novoServicoDTO);
}


