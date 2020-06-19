package com.desafio.codenation.services;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.repositories.UserRepositorie;
import com.querydsl.core.types.Predicate;
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

    public Page<User> getUsers(Predicate predicate, Pageable pageable) {
        return userRepositorie.findAll(predicate, pageable);
    }

    public User insert(User user) {
        return userRepositorie.save(user);
    }

    public User updateUser(Long id, User newUser) {
        User user = getUser(id);
        updtUser(user, newUser);
        return userRepositorie.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUser(id);
        userRepositorie.deleteById(id);
    }

    private void updtUser(User user, User newUser) {
        if (user.getEmail() != newUser.getEmail()) {
            user.setEmail(newUser.getEmail());
        }
        if (user.getPassword() != newUser.getPassword()) {
            user.setPassword(newUser.getPassword());
        }
        user.setPerfis(newUser.getPerfis());
    }
}
