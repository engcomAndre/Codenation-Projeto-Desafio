package com.desafio.codenation.services;


import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder bp;

    @Autowired
    private UserRepositorie userRepositorie;

    public void instantiateTestDatabase() throws ParseException {

        User userA = new User("menino@gmail.com", bp.encode("123456"));
        userA.addPerfil(TypeUser.ADMIN);
        userA.addPerfil(TypeUser.COMMON_USER);

        User userB = new User("menino2@gmail.com", bp.encode("654321"));
        userB.addPerfil(TypeUser.COMMON_USER);

        userRepositorie.saveAll(Arrays.asList(userA, userB));

    }

}
