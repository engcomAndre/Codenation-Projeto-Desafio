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
    private OrigemRepositorie origemRepositorie;

    public void instantiateTestDatabase() throws ParseException {

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
        Sistema sistemaA = Sistema.builderSistema()
                .nome("Nome Sistema " + ++i)
                .descricao("Descrição do Sistema " + i)
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .chave("15347144-f328-49fd-a1d2-e325c9d81adc")
                .password("123456")
                .ativo(true)
                .build();


        Servico serviceA = Servico.builderServico()
                .nome("Nome Servico " + ++i)
                .descricao("Descrição do Servico " + i)
                .chave("25347144-f328-49fd-a1d2-e325c9d81adc")
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .password("123456")
                .ativo(true)
                .build();

        Servico serviceB = Servico.builderServico()
                .nome("Nome Servico " + ++i)
                .descricao("Descrição do Servico " + i)
                .chave("35347144-f328-49fd-a1d2-e325c9d81adc")
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .password("123456")
                .ativo(true)
                .build();


        Sistema sistemaC = Sistema.builderSistema()
                .nome("Nome Sistema " + ++i)
                .descricao("Descrição do Sistema " + i)
                .chave("45347144-f328-49fd-a1d2-e325c9d81adc")
                .perfis(new HashSet(Arrays.asList(TypeUser.UNDEFINED)))
                .password("123456")
                .ativo(true)
                .build();


        Evento eventoA = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logA)
                .level(TypeLevel.ERROR)
                .origem(sistemaA)
                .quantidade(i)
                .build();

        logA.setEvento(eventoA);

        Evento eventoB = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logB)
                .level(TypeLevel.WARNING)
                .origem(sistemaC)
                .quantidade(i)
                .build();

        logB.setEvento(eventoB);

        Evento eventoC = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logC)
                .level(TypeLevel.INFO)
                .origem(serviceA)
                .quantidade(i)
                .build();

        logC.setEvento(eventoC);

        Evento eventoD = Evento.builder()
                .descricao("Descrição de uma evento " + ++i)
                .log(logD)
                .level(TypeLevel.INFO)
                .origem(serviceA)
                .quantidade(i)
                .build();

        logD.setEvento(eventoD);

        sistemaA.setEventos(Collections.singletonList(eventoA));
        sistemaC.setEventos(Collections.singletonList(eventoB));
        serviceA.setEventos(Collections.singletonList(eventoC));
        serviceB.setEventos(Collections.singletonList(eventoD));

        userRepositorie.saveAll(Arrays.asList(userA, userB));
        origemRepositorie.saveAll(Arrays.asList(sistemaA, serviceA, sistemaC, serviceB));
        eventoRepositorie.saveAll(Arrays.asList(eventoA, eventoB, eventoC, eventoD));
        logRepositorie.saveAll(Arrays.asList(logA, logB, logC, logD));
    }

}
