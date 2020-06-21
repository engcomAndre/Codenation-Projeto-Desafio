package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origem.DTO.NovoSistemaDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaListDto;
import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.domain.origem.mapper.SistemaMapper;
import com.desafio.codenation.services.SistemaService;
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
public class SistemaResources {

    private final SistemaService sistemaService;

    private final SistemaMapper sistemaMapper;

    public SistemaResources(SistemaService sistemaService, SistemaMapper sistemaMapper) {
        this.sistemaService = sistemaService;
        this.sistemaMapper = sistemaMapper;
    }
    @ApiOperation(value = "Busca de Sistema por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SistemaDTO> getSistemaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(sistemaMapper.map(sistemaService.getSistema(id)));
    }

    @ApiOperation(value = "Busca de Sistemas por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<SistemaListDto>> getSistemas(
            @QuerydslPredicate(root = Sistema.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        sistemaService
                                .getSistemas(predicate, pageable).stream()
                                .map(sistemaMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    @ApiOperation(value = "Cadastro de um novo Sistema.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertSistema(@Valid @RequestBody NovoSistemaDTO novoSistemaDTO) {
        Sistema sistema = sistemaService.insert(sistemaMapper.map(novoSistemaDTO));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sistema.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
    @ApiOperation(value = "Atualizar um  Sistema existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSistema(@Valid @PathVariable("id") Long id, @RequestBody NovoSistemaDTO novoSistemaDTO) {
        sistemaService.updateSistema(id, sistemaMapper.map(novoSistemaDTO));
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remover um  Sistema existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover sistemas com associações."),
            @ApiResponse(code = 404, message = "Sistema não encontrado")})
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSistema(@PathVariable("id") Long id) {
        sistemaService.deleteSistema(id);
        return ResponseEntity.noContent().build();
    }

}
