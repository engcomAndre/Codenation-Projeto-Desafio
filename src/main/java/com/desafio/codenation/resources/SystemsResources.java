package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origin.DTO.NewSystemsDTO;
import com.desafio.codenation.domain.origin.DTO.SystemsDTO;
import com.desafio.codenation.domain.origin.DTO.SistemaListDto;
import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.origin.mapper.SystemsMapper;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.services.SystemsService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Sistemas"}, value = "Recursos de Sistemas", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("sistema")
public class SystemsResources {

    private final SystemsService systemsService;

    private final SystemsMapper systemsMapper;

    public SystemsResources(SystemsService systemsService, SystemsMapper systemsMapper) {
        this.systemsService = systemsService;
        this.systemsMapper = systemsMapper;
    }

    @ApiOperation(value = "Buscar Sistemas",notes = "Busca de Sistema por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SystemsDTO> getSistemaById(@PathVariable Long id) {
//        SystemsDTO systemsDTO = systemsMapper.map(systemsService.getSistema(id));
        return ResponseEntity.ok().body(systemsMapper.mapToDetails(systemsService.getSistema(id)));
    }

    @ApiOperation(value = "Buscar Sistemas",notes = "Busca de Sistemas por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<SistemaListDto>> getSistemas(
            @QuerydslPredicate(root = Systems.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        systemsService
                                .getSistemas(predicate, pageable).stream()
                                .map(systemsMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }


    @ApiOperation(value = "Cadastrar Sistemas",notes = "Cadastro de um novo Sistema.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertSistema(@Valid @RequestBody NewSystemsDTO newSystemsDTO) {
        Systems systems = systemsMapper.map(newSystemsDTO);
        systems = systemsService.insert(systems);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(systems.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
    @ApiOperation(value = "Atualizar Sistemas",notes = "Atualizar um  Sistema existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSistema(@Valid @PathVariable("id") Long id, @RequestBody NewSystemsDTO newSystemsDTO) {
        systemsService.updateSistema(id, systemsMapper.map(newSystemsDTO));
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remover Sistemas",notes = "Remover um  Sistema existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover sistemas com associações."),
            @ApiResponse(code = 404, message = "Sistema não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSistema(@PathVariable("id") Long id) {
        systemsService.deleteSistema(id);
        return ResponseEntity.noContent().build();
    }

}
