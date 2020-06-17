package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositorie extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
