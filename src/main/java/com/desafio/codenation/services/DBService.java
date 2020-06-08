package com.desafio.codenation.services;


import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private UserRepositorie userRepositorie;
    @Autowired
    private EventoRepositorie eventoRepositorie;
    @Autowired
    private SistemaRepositorie sistemaRepositorie;
    @Autowired
    private LogRepositorie logRepositorie;
    @Autowired
    private OriginRepositorie originRepositorie;

    public void instantiateTestDatabase() throws ParseException {

        User userA = new User("menino@gmail.com", "123456");
        userA.addPerfil(TypeUser.ADMIN);
        userA.addPerfil(TypeUser.COMMON_USER);

        User userB = new User("menino2@gmail.com", "654321");
        userB.addPerfil(TypeUser.COMMON_USER);

        List<Log> logsA = Arrays.asList(new Log("descrição log A"));
        List<Log> logsB = Arrays.asList(new Log("descrição log B1"), new Log("descrição log B2"));
        List<Log> logsC = Arrays.asList(new Log("descrição log C1"), new Log("descrição log C2"), new Log("descrição log C3"));

        int i = 0;
        Sistema sistemaA = new Sistema("Nome Sistema " + ++i, LocalDate.now(), "Descrição do Sistema " + i, "35347144-f328-49fd-a1d2-e325c9d81adc", "123456");
        Servico serviceA = new Servico("Nome Servico " + ++i, LocalDate.now(), "Descrição do Serviço " + i, "46379cba-6690-4956-94f6-b420caf55016", "123456");
        Sistema sistemaC = new Sistema("Nome Sistema " + ++i, LocalDate.now(), "Descrição do Sistema " + i, "075f36d5-0a16-4f18-80ba-a7cbe9b9676d", "123456");

        Evento eventoA = new Evento("Descrição de uma evento " + ++i, logsA, TypeLevel.ERROR, sistemaA, LocalDate.now(), i);
        logsA.forEach(log -> log.setEvent(eventoA));

        Evento eventoB = new Evento("Descrição de uma evento " + ++i, logsB, TypeLevel.WARNING, sistemaC, LocalDate.now(), i);
        logsB.forEach(log -> log.setEvent(eventoB));

        Evento eventoC = new Evento("Descrição de uma evento " + ++i, logsC, TypeLevel.INFO, serviceA, LocalDate.now(), i);
        logsC.forEach(log -> log.setEvent(eventoC));

        sistemaA.setEventos(Collections.singletonList(eventoA));
        sistemaC.setEventos(Collections.singletonList(eventoB));
        serviceA.setEventos(Collections.singletonList(eventoC));

        userRepositorie.saveAll(Arrays.asList(userA, userB));
        originRepositorie.saveAll(Arrays.asList(sistemaA, serviceA, sistemaC));
        eventoRepositorie.saveAll(Arrays.asList(eventoA, eventoB, eventoC));
        logsA.forEach(log -> logRepositorie.save(log));
        logsB.forEach(log -> logRepositorie.save(log));
        logsC.forEach(log -> logRepositorie.save(log));

    }

}
