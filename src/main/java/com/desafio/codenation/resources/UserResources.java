package com.desafio.codenation.resources;

import com.desafio.codenation.domain.user.DTO.NovoUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.domain.user.mapper.NovoUserMapper;
import com.desafio.codenation.domain.user.mapper.UserMapper;
import com.desafio.codenation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getContacts(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(userMapper.map(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        userService
                                .getUsers(pageable).stream()
                                .map(userMapper::map)
                                .collect(Collectors.toList())));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody NovoUserDTO novoUser) {

        User user = userService.insert(novoUserMapper.map(novoUser));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/typeuser")
    public ResponseEntity<List<TypeUser>> getTypeUsers() {
        return ResponseEntity.ok().body(Arrays.asList(TypeUser.values()));
    }

}
