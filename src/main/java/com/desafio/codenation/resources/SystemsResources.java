package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origin.DTO.NewSystemsDTO;
import com.desafio.codenation.domain.origin.DTO.SistemaListDto;
import com.desafio.codenation.domain.origin.DTO.SystemsDTO;
import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.origin.mapper.SystemsMapper;
import com.desafio.codenation.resources.interfaces.SystemsResourcesContract;
import com.desafio.codenation.services.SystemsService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("sistema")
public class SystemsResources implements SystemsResourcesContract {

    private final SystemsService systemsService;

    private final SystemsMapper systemsMapper;

    public SystemsResources(SystemsService systemsService, SystemsMapper systemsMapper) {
        this.systemsService = systemsService;
        this.systemsMapper = systemsMapper;
    }

    public ResponseEntity<SystemsDTO> getSistemaById(Long id) {
        return ResponseEntity.ok().body(systemsMapper.mapToDetails(systemsService.getSistema(id)));
    }


    public ResponseEntity<Page<SistemaListDto>> getSistemas(Predicate predicate, Long id, String nome, String descricao, String chave, String password, LocalDateTime createdAt, String sort, String page, Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        systemsService
                                .getSistemas(predicate, pageable).stream()
                                .map(systemsMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }


    @ApiOperation(value = "Cadastrar Sistemas", notes = "Cadastro de um novo Sistema.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertSistema(NewSystemsDTO newSystemsDTO) {
        Systems systems = systemsMapper.map(newSystemsDTO);
        systems = systemsService.insert(systems);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(systems.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Void> updateSistema(Long id, NewSystemsDTO
            newSystemsDTO) {
        systemsService.updateSistema(id, systemsMapper.map(newSystemsDTO));
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteSistema(Long id) {
        systemsService.deleteSistema(id);
        return ResponseEntity.noContent().build();
    }

}
