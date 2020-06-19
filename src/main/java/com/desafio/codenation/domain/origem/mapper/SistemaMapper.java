package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.DTO.NovoSistemaDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaListDto;
import com.desafio.codenation.domain.origem.Sistema;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SistemaMapper {
    @InheritConfiguration(name = "originDTO")
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")

    })
    SistemaDTO map(Sistema sistema);

    SistemaListDto mapForFindAll(Sistema sistema);

    @InheritConfiguration(name = "originDTO")
    Sistema map(NovoSistemaDTO novoServicoDTO);

}
