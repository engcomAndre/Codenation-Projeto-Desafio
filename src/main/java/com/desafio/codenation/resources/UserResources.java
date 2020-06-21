package com.desafio.codenation.resources;

import com.desafio.codenation.domain.user.DTO.NovoUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.domain.user.mapper.NovoUserMapper;
import com.desafio.codenation.domain.user.mapper.UserMapper;
import com.desafio.codenation.services.UserService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Usuários"}, value = "Recursos de Usuários", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("user")
public class UserResources {

    private final UserService userService;

    private final UserMapper userMapper;

    private final NovoUserMapper novoUserMapper;

    @Autowired
    public UserResources(UserService userService, UserMapper userMapper, NovoUserMapper novoUserMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.novoUserMapper = novoUserMapper;
    }

    @ApiOperation(value = "Busca de Usuários por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getContacts(@PathVariable Long id) {
        return ResponseEntity.ok().body(userMapper.map(userService.getUser(id)));
    }

    @ApiOperation(value = "Busca de Usuários por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        userService
                                .getUsers(predicate, pageable).stream()
                                .map(userMapper::map)
                                .collect(Collectors.toList())));
    }


    @ApiOperation(value = "Cadastro de um novo usuário.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insertUser(@Valid @RequestBody NovoUserDTO novoUser) {

        User user = userService.insert(novoUserMapper.map(novoUser));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Atualizar um  Usuário existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @Valid @RequestBody NovoUserDTO novoUser) {
        User user = userMapper.map(novoUser);

        userService.updateUser(id, user);

        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Remover um  Usuário existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover usuario com associações."),
            @ApiResponse(code = 404, message = "Usuário não encontrado")})
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Obter os tipos de usuários disponíveis.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/typeuser")
    public ResponseEntity<List<TypeUser>> getTypeUsers() {
        return ResponseEntity.ok().body(Arrays.asList(TypeUser.values()));
    }

}
