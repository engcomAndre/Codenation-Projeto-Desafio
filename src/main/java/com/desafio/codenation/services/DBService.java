package com.desafio.codenation.services;


import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.user.Sistema;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.EventoRepositorie;
import com.desafio.codenation.repositories.SistemaRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder bp;

    @Autowired
    private UserRepositorie userRepositorie;

    @Autowired
    private EventoRepositorie eventoRepositorie;

    @Autowired
    private SistemaRepositorie sistemaRepositorie;

    public void instantiateTestDatabase() throws ParseException {

        User userA = new User("menino@gmail.com", bp.encode("123456"));
        userA.addPerfil(TypeUser.ADMIN);
        userA.addPerfil(TypeUser.COMMON_USER);

        User userB = new User("menino2@gmail.com", bp.encode("654321"));
        userB.addPerfil(TypeUser.COMMON_USER);

        int i = 0;
        Evento eventoA = new Evento("Descrição de uma evento " + ++i, "Log do evento " + i, TypeLevel.ERROR, "Sistema " + i, LocalDate.now(), i);
        Evento eventoB = new Evento("Descrição de uma evento " + ++i, "Log do evento " + i, TypeLevel.WARNING, "Sistema " + i, LocalDate.now(), i);
        Evento eventoC = new Evento("Descrição de uma evento " + ++i, "Log do evento " + i, TypeLevel.INFO, "Serviço " + i, LocalDate.now(), i);

        int j = 0;
        Sistema sistemaA = new Sistema("Nome Sistema " + ++i, LocalDate.now(), "Descrição do Sistema " + i);
        Sistema sistemaB = new Sistema("Nome Sistema " + ++i, LocalDate.now(), "Descrição do Sistema " + i);
        Sistema sistemaC = new Sistema("Nome Sistema " + ++i, LocalDate.now(), "Descrição do Sistema " + i);

        userRepositorie.saveAll(Arrays.asList(userA, userB));
        eventoRepositorie.saveAll(Arrays.asList(eventoA, eventoB, eventoC));
        sistemaRepositorie.saveAll(Arrays.asList(sistemaA, sistemaB, sistemaC));

    }

}
