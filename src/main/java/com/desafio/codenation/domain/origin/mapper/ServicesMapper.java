package com.desafio.codenation.domain.origin.mapper;

import com.desafio.codenation.domain.origin.DTO.NewServiceDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesListDto;
import com.desafio.codenation.domain.origin.Services;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    @InheritConfiguration(name = "originDTO")
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "events", target = "events"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")

    })
    ServicesDTO map(Services services);

    ServicesListDto mapForFindAll(Services services);
    @InheritConfiguration(name = "originDTO")
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "key", target = "key"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "password", target = "password")
    })
    Services map(NewServiceDTO newServiceDTO);

}

