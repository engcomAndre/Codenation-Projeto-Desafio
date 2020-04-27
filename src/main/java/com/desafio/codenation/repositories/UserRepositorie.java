package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorie extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
