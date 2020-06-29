package com.desafio.codenation.services;


import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.EventsRepositorie;
import com.desafio.codenation.repositories.LogsRepositorie;
import com.desafio.codenation.repositories.OriginsRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void instantiateTestDatabase() {
        insertAdmin();

        int quantity = 100;
        Random rand = new Random();

        List<User> userList = new ArrayList<>();
        List<Log> logs = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        List<Origins> origins = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {

            User user = User.builder()
                    .email("user_" + i + "@commomuser.com")
                    .password("@" + "user_" + i)
                    .grants(new HashSet<>(Collections.singletonList(TypeUser.COMMON_USER)))
                    .build();

            userList.add(user);

            int ranMin = rand.nextInt(userList.size());
            int ranMax = rand.ints(ranMin, userList.size()).limit(1).findFirst().orElseThrow();

            Origins originsEnt;

            if (rand.nextBoolean()) {
                originsEnt = Services.builderServico()
                        .name("Nome do Servico " + i)
                        .description("Descrição do Serviço " + i)
                        .password("123456")
                        .active(true)
                        .key(UUID.randomUUID().toString().replace("-", ""))
                        .grants(new HashSet<>(Collections.singleton(TypeUser.UNDEFINED)))
                        .users(userList.subList(ranMin, ranMax))
                        .build();
            } else {
                originsEnt = Systems.builderSistema()
                        .name("Nome do Sistema " + i)
                        .description("Descrição do Sistema " + i)
                        .password("123456")
                        .active(true)
                        .key(UUID.randomUUID().toString().replace("-", ""))
                        .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
                        .users(userList.subList(ranMin, ranMax))
                        .build();
            }

            origins.add(originsEnt);

            Events event;
            Log log;

            if (origins.size() > 0) {
                log = Log.builder()
                        .description("Descrição de um log de evento " + UUID.randomUUID().toString().replace("-", ""))
                        .build();

                event = Events.builder()
                        .description("Descrição de um evento " + UUID.randomUUID().toString().replace("-", ""))
                        .level(TypeLevel
                                .toEnum(1 + rand
                                        .nextInt(TypeLevel.values().length)))
                        .quantity(rand.nextInt(50))
                        .log(log)
                        .origins(origins.get(rand.nextInt(origins.size())))
                        .build();

                log.setEvents(event);
                events.add(event);
                logs.add(log);
                userRepositorie.save(user);
                originsRepositorie.save(originsEnt);
                eventsRepositorie.save(event);
                logsRepositorie.save(log);
            }
        }

    }


    private void insertAdmin() {
        User userA = User.builder()
                .email("admin@admin.com")
                .password("@admin")
                .grants(new HashSet<>(Arrays.asList(TypeUser.ADMIN, TypeUser.COMMON_USER)))
                .build();
        userRepositorie.save(userA);
    }

}
