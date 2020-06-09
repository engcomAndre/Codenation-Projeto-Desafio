package com.desafio.codenation.domain.eventos.mapper;

import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NovoEventoMapper {
    @Mappings({
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "level", target = "level"),
            @Mapping(source = "origemId", target = "origem.id"),
            @Mapping(source = "quantidade", target = "quantidade")
    })
    Evento map(NovoEventoDTO novoEventoDTO);

    List<Evento> map(List<NovoEventoDTO> novoEventoDTOS);
}
