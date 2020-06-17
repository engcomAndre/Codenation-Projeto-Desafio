package com.desafio.codenation.services;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepositorie userRepositorie;

    public User getUser(Long id) {
        return userRepositorie.findById(id).orElse(null);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepositorie.findAll(pageable);
    }

    public User insert(User user) {
        return userRepositorie.save(user);
    }
}
