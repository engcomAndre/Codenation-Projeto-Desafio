package com.desafio.codenation.services;


import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.EventsRepositorie;
import com.desafio.codenation.repositories.LogsRepositorie;
import com.desafio.codenation.repositories.OriginsRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class DBService {

    private final UserRepositorie userRepositorie;
    private final EventsRepositorie eventsRepositorie;
    private final LogsRepositorie logsRepositorie;
    private final OriginsRepositorie originsRepositorie;

    public DBService(UserRepositorie userRepositorie, EventsRepositorie eventsRepositorie, LogsRepositorie logsRepositorie, OriginsRepositorie originsRepositorie) {
        this.userRepositorie = userRepositorie;
        this.eventsRepositorie = eventsRepositorie;
        this.logsRepositorie = logsRepositorie;
        this.originsRepositorie = originsRepositorie;
    }

    public void instantiateTestDatabase() {

        User userA = User.builder()
                .email("admin@admin.com")
                .password("123456")
                .perfis(new HashSet(Arrays.asList(TypeUser.ADMIN, TypeUser.COMMON_USER)))
                .build();

        User userB = User.builder()
                .email("meninobom@gmail.com")
                .password("654321")
                .perfis(new HashSet(Arrays.asList(TypeUser.COMMON_USER)))
                .build();

        Log logA = Log.builder().descricao("descrição log A").build();
        Log logB = Log.builder().descricao("descrição log B").build();
        Log logC = Log.builder().descricao("descrição log C").build();
        Log logD = Log.builder().descricao("descrição log D").build();

        int i = 0;
        Systems systemsA = Systems.builderSistema()
                .nome("Nome Sistema " + ++i)
                .descricao("Descrição do Sistema " + i)
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .chave("15347144-f328-49fd-a1d2-e325c9d81adc")
                .password("123456")
                .ativo(true)
                .build();


        Services serviceA = Services.builderServico()
                .nome("Nome Servico " + ++i)
                .descricao("Descrição do Servico " + i)
                .chave("25347144-f328-49fd-a1d2-e325c9d81adc")
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .password("123456")
                .ativo(true)
                .build();

        Services serviceB = Services.builderServico()
                .nome("Nome Servico " + ++i)
                .descricao("Descrição do Servico " + i)
                .chave("35347144-f328-49fd-a1d2-e325c9d81adc")
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .password("123456")
                .ativo(true)
                .build();


        Systems systemsC = Systems.builderSistema()
                .nome("Nome Sistema " + ++i)
                .descricao("Descrição do Sistema " + i)
                .chave("45347144-f328-49fd-a1d2-e325c9d81adc")
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .password("123456")
                .ativo(true)
                .build();


        Events eventsA = Events.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logA)
                .level(TypeLevel.ERROR)
                .origins(systemsA)
                .quantidade(i)
                .build();

        logA.setEvents(eventsA);

        Events eventsB = Events.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logB)
                .level(TypeLevel.WARNING)
                .origins(systemsC)
                .quantidade(i)
                .build();

        logB.setEvents(eventsB);

        Events eventsC = Events.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logC)
                .level(TypeLevel.INFO)
                .origins(serviceA)
                .quantidade(i)
                .build();

        logC.setEvents(eventsC);

        Events eventsD = Events.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logD)
                .level(TypeLevel.INFO)
                .origins(serviceA)
                .quantidade(i)
                .build();

        logD.setEvents(eventsD);

        systemsA.setEvents(Collections.singletonList(eventsA));
        systemsC.setEvents(Collections.singletonList(eventsB));
        serviceA.setEvents(Collections.singletonList(eventsC));
        serviceB.setEvents(Collections.singletonList(eventsD));

        userRepositorie.saveAll(Arrays.asList(userA, userB));
        originsRepositorie.saveAll(Arrays.asList(systemsA, serviceA, systemsC, serviceB));
        eventsRepositorie.saveAll(Arrays.asList(eventsA, eventsB, eventsC, eventsD));
        logsRepositorie.saveAll(Arrays.asList(logA, logB, logC, logD));
    }

}
