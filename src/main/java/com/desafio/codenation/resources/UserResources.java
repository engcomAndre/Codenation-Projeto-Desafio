package com.desafio.codenation.resources;

import com.desafio.codenation.domain.user.DTO.NewUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.domain.user.mapper.NewUserMapper;
import com.desafio.codenation.domain.user.mapper.UserMapper;
import com.desafio.codenation.resources.interfaces.UserResourcesContract;
import com.desafio.codenation.services.UserService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.desafio.codenation.resources.utils.MapDetaisForUsers.mapDetails;

@RestController
@RequestMapping("user")
public class UserResources implements UserResourcesContract {

    private final UserService userService;

    private final UserMapper userMapper;

    private final NewUserMapper newUserMapper;

    @Autowired
    public UserResources(UserService userService, UserMapper userMapper, NewUserMapper newUserMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.newUserMapper = newUserMapper;
    }


    public ResponseEntity<UserDTO> getUser(Long id) {
        return ResponseEntity.ok().body(mapDetails(userService.getUser(id), userMapper));
    }


    public ResponseEntity<Page<UserDTO>> getUsers(
            @QuerydslPredicate(root = User.class) Predicate predicate,
            Long id,
            Long email,
            Long perfis,
            Long origins,
            String sort,
            String page, Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        userService
                                .getUsers(predicate, pageable).stream()
                                .map(userMapper::map)
                                .collect(Collectors.toList())));
    }

    public ResponseEntity<Void> insertUser(@Valid @RequestBody NewUserDTO novoUser) {

        User user = userService.insert(newUserMapper.map(novoUser));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Void> updateUser(Long id, NewUserDTO novoUser) {
        User user = userMapper.map(novoUser);

        userService.updateUser(id, user);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<TypeUser>> getTypeUsers() {
        return ResponseEntity.ok().body(Arrays.asList(TypeUser.values()));
    }
}
