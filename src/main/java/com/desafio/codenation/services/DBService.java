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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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

        User userA = User.builder()
                .username("menino@gmail.com")
                .password("123456")
                .perfis(new HashSet(Arrays.asList(TypeUser.ADMIN, TypeUser.COMMON_USER)))
                .build();

        User userB = User.builder()
                .username("menino2@gmail.com")
                .password("654321")
                .perfis(new HashSet(Arrays.asList(TypeUser.COMMON_USER)))
                .build();

        Log logA = Log.builder().descricao("descrição log A").build();
        Log logB1 = Log.builder().descricao("descrição log B").build();
        Log logB2 = Log.builder().descricao("descrição log B").build();
        Log logC1 = Log.builder().descricao("descrição log C").build();
        Log logC2 = Log.builder().descricao("descrição log C").build();
        Log logC3 = Log.builder().descricao("descrição log C").build();

        List<Log> logsA = Arrays.asList(logA);
        List<Log> logsB = Arrays.asList(logB1, logB2);
        List<Log> logsC = Arrays.asList(logC1, logC2, logC3);

        int i = 0;
        Sistema sistemaA = Sistema.builder()
                .nome("Nome Sistema " + ++i)
                .descricao("Descrição do Sistema " + i)
                .username("35347144-f328-49fd-a1d2-e325c9d81adc")
                .password("123456")
                .build();


        Servico serviceA = Servico.builder()
                .nome("Nome Servico " + ++i)
                .descricao("Descrição do Servico " + i)
                .username("15347144-f328-49fd-a1d2-e325c9d81adc")
                .password("123456")
                .build();

        Sistema sistemaC = Sistema.builder()
                .nome("Nome Sistema " + ++i)
                .descricao("Descrição do Sistema " + i)
                .username("25347144-f328-49fd-a1d2-e325c9d81adc")
                .password("123456")
                .build();


        Evento eventoA = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .logs(logsA)
                .level(TypeLevel.ERROR)
                .origem(sistemaA)
                .quantidade(i)
                .build();


        logsA.forEach(log -> log.setEvento(eventoA));

        Evento eventoB = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .logs(logsB)
                .level(TypeLevel.WARNING)
                .origem(sistemaC)
                .quantidade(i)
                .build();

        logsB.forEach(log -> log.setEvento(eventoB));

        Evento eventoC = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .logs(logsC)
                .level(TypeLevel.INFO)
                .origem(serviceA)
                .quantidade(i)
                .build();

        logsC.forEach(log -> log.setEvento(eventoC));

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
