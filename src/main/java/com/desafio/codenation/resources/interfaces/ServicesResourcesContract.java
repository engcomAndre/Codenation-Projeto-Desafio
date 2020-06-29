package com.desafio.codenation.resources.interfaces;

import com.desafio.codenation.domain.origin.DTO.NewServiceDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesListDto;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.user.enums.TypeUser;
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

@Api(tags = {"3 - Serviços"}, value = "Recursos de Serviços", hidden = false, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public interface ServicesResourcesContract {

    @ApiOperation(value = "Buscar Serviço", notes = "Busca de Serviços por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ServicesDTO> getServicoById(@PathVariable Long id);

    @ApiOperation(value = "Buscar Serviços", notes = "Busca de Serviços por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<ServicesListDto>> getServicos(
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

    @ApiOperation(value = "Cadastrar Serviços", notes = "Cadastro de um novo Serviço.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> insertService(@Valid @RequestBody NewServiceDTO newServiceDTO);

    @ApiOperation(value = "Atualizar Servico", notes = "Atualizar um  Serviço existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateServico(@PathVariable("id") Long id, @Valid @RequestBody NewServiceDTO newServiceDTO);

    @ApiOperation(value = "Buscar Tipos de Servico", notes = "Remover um  Serviço existente por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover serviços com associações."),
            @ApiResponse(code = 404, message = "Serviço não encontrado")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);
}
