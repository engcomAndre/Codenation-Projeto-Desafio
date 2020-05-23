package com.desafio.codenation.resources;

import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.dto.UserDto;
import com.desafio.codenation.dto.UserDtoExpose;
import com.desafio.codenation.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserResources {

    private final UserService userService;

    public UserResources(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoExpose> getUsersById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new UserDtoExpose(userService.getUser(id)));
    }

    @GetMapping
    public ResponseEntity<Page<UserDtoExpose>> getUsers(Pageable page) {
        return ResponseEntity.ok().body(userService.getUsers(page).map(UserDtoExpose::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoExpose> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(new UserDtoExpose(userService.updateUser(id, userDto)));
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDto userDto) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userService.insert(userDto))
                .toUri()).build();

    }

    @GetMapping("/perfil")
    public ResponseEntity<Map<String, TypeUser[]>> getAllDisposeblePerfis() {
        return ResponseEntity.ok().body(Map.of("perfis", TypeUser.values()));
    }
}
