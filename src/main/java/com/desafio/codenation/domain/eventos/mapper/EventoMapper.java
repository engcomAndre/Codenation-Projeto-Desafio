package com.desafio.codenation.domain.eventos.mapper;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.DTO.EventoListDto;
import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.logs.DTO.LogDTO;
import com.desafio.codenation.domain.logs.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mappings({
            @Mapping(source = "log", target = "log"),
//            @Mapping(source = "origem.id", target = "origemId")
    })
    EventoDTO map(Evento evento);

    EventoListDto mapForFindAll(Evento evento);


    @Mappings({
            @Mapping(source = "origemId", target = "origem.id")
    })
    Evento map(NovoEventoDTO novoEventoDTO);
}





