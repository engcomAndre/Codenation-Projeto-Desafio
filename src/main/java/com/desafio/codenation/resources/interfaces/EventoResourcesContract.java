package com.desafio.codenation.resources.interfaces;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.DTO.EventoListDto;
import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface EventoResourcesContract {

    @ApiOperation(value = "Buscar Eventos", notes = "Busca de Eventos por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id);

    @ApiOperation(value = "Buscar Eventos", notes = "Busca de Eventos por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<Page<EventoListDto>> getEventos(
            @QuerydslPredicate(root = Evento.class) Predicate predicate,
            @ApiParam @RequestParam(name = "id", required = false) Long id,
            @ApiParam @RequestParam(name = "descricao", required = false) Long descricao,
            @ApiParam @RequestParam(name = "level", required = false) Long level,
            @ApiParam @RequestParam(name = "quantidade", required = false) Long quantidade,
            Pageable pageable);

    @ApiOperation(value = "Cadastrar Eventos", notes = "Cadastro de um novo evento.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN') or hasRole('ROLE_UNDEFINED')")
    @PostMapping
    ResponseEntity<Void> insertEvento(@Valid @RequestBody NovoEventoDTO novoEventoDTO);


    @ApiOperation(value = "Atualizar Eventos", notes = "Atualizar um  Evento existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateEvento(@PathVariable("id") Long id, @Valid @RequestBody NovoEventoDTO novoEventoDTO);

    @ApiOperation(value = "Remover Eventos", notes = "Remover um  Evento existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover eventos com associações."),
            @ApiResponse(code = 404, message = "Evento não encontrado")})
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEvento(@PathVariable("id") Long id);

    @ApiOperation(value = "Buscar Tipos de Eventos", notes = "Obter os tipos de Eventos disponíveis.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/event-level")
    ResponseEntity<List<TypeLevel>> getTypeLevelResponseEntity();
}
