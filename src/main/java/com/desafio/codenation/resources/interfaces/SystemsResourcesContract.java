package com.desafio.codenation.resources.interfaces;

import com.desafio.codenation.domain.origin.DTO.NewSystemsDTO;
import com.desafio.codenation.domain.origin.DTO.SistemaListDto;
import com.desafio.codenation.domain.origin.DTO.SystemsDTO;
import com.desafio.codenation.domain.origin.Services;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"4 - Sistemas"}, value = "Recursos de Sistemas", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public interface SystemsResourcesContract {


    @ApiOperation(value = "Buscar Sistemas", notes = "Busca de Sistema por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    ResponseEntity<SystemsDTO> getSistemaById(@PathVariable Long id);

    @ApiOperation(value = "Buscar Sistemas", notes = "Busca de Sistemas por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping
    ResponseEntity<Page<SistemaListDto>> getSistemas(
            @QuerydslPredicate(root = Services.class) Predicate predicate,
            @ApiParam @RequestParam(name = "id", required = false) Long id,
            @ApiParam @RequestParam(name = "nome", required = false) String nome,
            @ApiParam @RequestParam(name = "descricao", required = false) String descricao,
            @ApiParam @RequestParam(name = "chave", required = false) String chave,
            @ApiParam @RequestParam(name = "password", required = false) String password,
            @ApiParam @RequestParam(name = "createdAt", required = false) LocalDateTime createdAt,
            @ApiParam @RequestParam(name = "sort", required = false) String sort,
            @ApiParam @RequestParam(name = "page", required = false) String page,
            Pageable pageable);

    @ApiOperation(value = "Cadastrar Sistemas", notes = "Cadastro de um novo Sistema.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping
    ResponseEntity<Void> insertSistema(@Valid @RequestBody NewSystemsDTO newSystemsDTO);

    @ApiOperation(value = "Atualizar Sistemas", notes = "Atualizar um  Sistema existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PutMapping("/{id}")
    ResponseEntity<Void> updateSistema(@Valid @PathVariable("id") Long id, @RequestBody NewSystemsDTO
            newSystemsDTO);

    @ApiOperation(value = "Remover Sistemas", notes = "Remover um  Sistema existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover sistemas com associações."),
            @ApiResponse(code = 404, message = "Sistema não encontrado")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSistema(@PathVariable("id") Long id);
}
