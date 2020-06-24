package com.desafio.codenation.domain.events.mapper;

import com.desafio.codenation.domain.events.DTO.EventsDTO;
import com.desafio.codenation.domain.events.DTO.EventsListDto;
import com.desafio.codenation.domain.events.DTO.NewEventsDTO;
import com.desafio.codenation.domain.events.Events;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventsMapper {

    @Mappings({
            @Mapping(source = "log", target = "log"),
//            @Mapping(source = "origem.id", target = "origemId")
    })
    EventsDTO map(Events events);

    EventsListDto mapForFindAll(Events events);


    @Mappings({
            @Mapping(source = "origemId", target = "origins.id")
    })
    Events map(NewEventsDTO newEventsDTO);
}





