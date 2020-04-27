package com.desafio.codenation.services;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.dto.UserDto;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepositorie userRepositorie;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto getUser(Integer id) {
        UserDto userDto = userRepositorie.findById(id).map(user -> new UserDto(user.getEmail(), user.getPassword(),user.getPerfis())).orElseThrow(IllegalArgumentException::new);
        return userDto;
    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtos = userRepositorie.findAll().stream().map(user -> new UserDto(user.getEmail(), user.getPassword(),user.getPerfis())).collect(Collectors.toList());
        return userDtos;
    }

    public User fromDto(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return user;
    }

    public User insert(UserDto userDto) {
        User user = this.fromDto(userDto.getEmail(), userDto.getPassword());
        user = userRepositorie.save(user);
        return user;
    }
}
