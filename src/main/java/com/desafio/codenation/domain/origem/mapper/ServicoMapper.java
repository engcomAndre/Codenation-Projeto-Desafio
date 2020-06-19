package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.DTO.NovoServicoDTO;
import com.desafio.codenation.domain.origem.DTO.ServicoDTO;
import com.desafio.codenation.domain.origem.DTO.ServicoListDto;
import com.desafio.codenation.domain.origem.DTO.SistemaListDto;
import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.Sistema;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    @InheritConfiguration(name = "originDTO")
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "eventos", target = "eventos"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")

    })
    ServicoDTO map(Servico servico);

    ServicoListDto mapForFindAll(Servico servico);

    Servico map(NovoServicoDTO novoServicoDTO);

}

