package com.desafio.codenation.resources;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.dto.UserDto;
import com.desafio.codenation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserResources {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserDto> getContacts(@PathVariable Integer id) {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok().body(userDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto>userDtos = userService.getUsers();
        return ResponseEntity.ok().body(userDtos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@RequestBody UserDto userDto){
        User user = userService.insert(userDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }



}
