package com.desafio.codenation.services;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.dto.UserDto;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepositorie userRepositorie;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUser(Long id) {
        User user = userRepositorie.findById(id).orElse(null);
        return user;
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepositorie.findAll(pageable);
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

    public User updateUser(Long id, UserDto userDto) {
        User user = userRepositorie.findById(id).orElse(null);

        if (user != null) {
            if (!user.getEmail().equals(userDto.getEmail())) user.setEmail(user.getEmail());
            if (!user.getPassword().equals(bCryptPasswordEncoder.encode(userDto.getPassword())))
                user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            if (!user.getPerfis().containsAll(userDto.getPerfis())) user.setPerfis(userDto.getPerfis());
        }
        return userRepositorie.save(user);

    }
}
