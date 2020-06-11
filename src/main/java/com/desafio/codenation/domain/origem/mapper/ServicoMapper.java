package com.desafio.codenation.domain.origem.mapper;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.dto.ServicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),

    })
    ServicoDTO map(Servico servico);

    List<ServicoDTO> map(List<Servico> servicos);
}
