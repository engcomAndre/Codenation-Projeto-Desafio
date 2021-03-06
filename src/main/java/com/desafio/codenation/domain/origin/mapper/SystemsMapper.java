package com.desafio.codenation.domain.origin.mapper;

import com.desafio.codenation.domain.origin.DTO.NewSystemsDTO;
import com.desafio.codenation.domain.origin.DTO.SistemaListDto;
import com.desafio.codenation.domain.origin.DTO.SystemsDTO;
import com.desafio.codenation.domain.origin.Systems;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SystemsMapper {
    @InheritConfiguration(name = "originDTO")
//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "events", target = "events"),
//            @Mapping(source = "users", target = "users"),
//            @Mapping(source = "description", target = "description"),
//            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
//    })
    SystemsDTO mapToDetails(Systems systems);

    SistemaListDto mapForFindAll(Systems systems);

    //    @InheritConfiguration(name = "originDTO")
//    @Mappings({
//            @Mapping(source = "nome", target = "nome"),
//            @Mapping(source = "descricao", target = "descricao"),
//            @Mapping(source = "password", target = "password")
//    })
    Systems map(NewSystemsDTO newSystemsDTO);

}
