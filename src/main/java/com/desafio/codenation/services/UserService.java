package com.desafio.codenation.services;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.repositories.UserRepositorie;
import com.desafio.codenation.services.exception.DataIntegrityException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.desafio.codenation.services.utils.updtUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepositorie userRepositorie;

    public UserService(UserRepositorie userRepositorie) {
        this.userRepositorie = userRepositorie;
    }

    public User getUser(Long id) {
        return userRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<User> getUsers(Predicate predicate, Pageable pageable) {
        Page<User> userPage = userRepositorie.findAll(predicate, pageable);
        if (userPage.isEmpty()) {
            throw new ObjectNotFoundException("Usuários (s) não encontrado(s) para os parâmetros informados.");
        }
        return userPage;
    }

    public User insert(User user) {
        return userRepositorie.save(user);
    }

    public User updateUser(Long id, User newUser) {
        User user = getUser(id);
        updtUtils.updtUser(user, newUser);
        return userRepositorie.save(user);
    }

    public void deleteUser(Long id) {
        getUser(id);
        try {
            userRepositorie.deleteById(id);
        } catch (
                DataIntegrityException die) {
            throw new DataIntegrityException("Não é possível excluir um Sistema que possui registros de evento atrelados.");
        }
    }

}
