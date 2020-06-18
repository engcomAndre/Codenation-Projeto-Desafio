package com.desafio.codenation.domain.eventos.mapper;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "level", target = "level"),
            @Mapping(source = "origem.id", target = "origemId"),
            @Mapping(source = "log", target = "log"),
            @Mapping(source = "quantidade", target = "quantidade"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "modifiedData", target = "modifiedData", dateFormat = "yyyy-MM-dd HH:mm")
    })
    EventoDTO map(Evento evento);

    List<EventoDTO> map(List<Evento> evento);
}





