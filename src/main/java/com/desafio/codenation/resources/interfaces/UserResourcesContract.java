package com.desafio.codenation.resources.interfaces;

import com.desafio.codenation.domain.user.DTO.NewUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"2 - Usuários"}, value = "Recursos de Usuários", hidden = false, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public interface UserResourcesContract {

    @ApiOperation(value = "Buscar Usuário", notes = "Busca de Usuários por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable Long id);

    @ApiOperation(value = "Buscar Usuários", notes = "Busca de Usuários por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping
    ResponseEntity<Page<UserDTO>> getUsers(
            @QuerydslPredicate(root = User.class) Predicate predicate,
            @ApiParam @RequestParam(name = "id", required = false) Long id,
            @ApiParam @RequestParam(name = "email", required = false) Long email,
            @ApiParam @RequestParam(name = "perfis", required = false) Long perfis,
            @ApiParam @RequestParam(name = "origins", required = false) Long origins,
            @ApiParam @RequestParam(name = "sort", required = false) String sort,
            @ApiParam @RequestParam(name = "page", required = false) String page, Pageable pageable);

    @ApiOperation(value = "Cadastrar Usuários", notes = "Cadastro de um novo usuário.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping
    ResponseEntity<Void> insertUser(@Valid @RequestBody NewUserDTO novoUser);

    @ApiOperation(value = "Atualizar Usuário", notes = "Atualizar um  Usuário existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PutMapping("/{id}")
    ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @Valid @RequestBody NewUserDTO novoUser);

    @ApiOperation(value = "Remover Usuário", notes = "Remover um  Usuário existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover usuario com associações."),
            @ApiResponse(code = 404, message = "Usuário não encontrado")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Obter os tipos de usuários disponíveis.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping("/typeuser")
    ResponseEntity<List<TypeUser>> getTypeUsers();


}
