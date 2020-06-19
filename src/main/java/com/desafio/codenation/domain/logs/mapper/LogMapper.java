package com.desafio.codenation.domain.logs.mapper;

import com.desafio.codenation.domain.logs.DTO.NovoLog;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.logs.DTO.LogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    LogDTO map(Log log);

    List<LogDTO> map(List<Log> logs);

    Log map(NovoLog novoLog);
}
