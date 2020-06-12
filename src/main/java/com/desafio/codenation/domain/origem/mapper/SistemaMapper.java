package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.DTO.ServicoDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaDTO;
import com.desafio.codenation.domain.origem.Servico;
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
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),

    })
    SistemaDTO map(Servico servico);

    List<ServicoDTO> map(List<Servico> servicos);
}
