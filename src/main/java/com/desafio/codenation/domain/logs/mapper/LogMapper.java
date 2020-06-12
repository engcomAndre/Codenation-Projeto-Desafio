package com.desafio.codenation.domain.logs.mapper;

import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.logs.DTO.LogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "evento.id", target = "eventoId"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
    })
    LogDTO map(Log log);

    List<LogDTO> map(List<Log> logs);
}
