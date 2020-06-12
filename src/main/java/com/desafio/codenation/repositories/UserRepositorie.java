package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorie extends BaseRepository<User, Long> {
    User findByEmail(String email);
}
