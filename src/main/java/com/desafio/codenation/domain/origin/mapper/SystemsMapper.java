package com.desafio.codenation.domain.origin.mapper;

import com.desafio.codenation.domain.origin.DTO.NewSystemsDTO;
import com.desafio.codenation.domain.origin.DTO.SistemaListDto;
import com.desafio.codenation.domain.origin.DTO.SystemsDTO;
import com.desafio.codenation.domain.origin.Systems;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SystemsMapper {
    @InheritConfiguration(name = "originDTO")
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")

    })
    SystemsDTO map(Systems systems);

    SistemaListDto mapForFindAll(Systems systems);

    @InheritConfiguration(name = "originDTO")
    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "password", target = "password")
    })
    Systems map(NewSystemsDTO newSystemsDTO);

}
