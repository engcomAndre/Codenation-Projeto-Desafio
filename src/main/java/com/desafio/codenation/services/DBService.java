package com.desafio.codenation.services;


import com.desafio.codenation.domain.OriginUser;
import com.desafio.codenation.domain.OriginUserPk;
import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.EventsRepositorie;
import com.desafio.codenation.repositories.OriginUserRepositorie;
import com.desafio.codenation.repositories.OriginsRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DBService {

    private final UserRepositorie userRepositorie;
    private final EventsRepositorie eventsRepositorie;
    private final OriginUserRepositorie originUserRepositorie;
    private final OriginsRepositorie originsRepositorie;

    public DBService(UserRepositorie userRepositorie, EventsRepositorie eventsRepositorie, OriginUserRepositorie originUserRepositorie, OriginsRepositorie originsRepositorie) {
        this.userRepositorie = userRepositorie;
        this.eventsRepositorie = eventsRepositorie;
        this.originUserRepositorie = originUserRepositorie;
        this.originsRepositorie = originsRepositorie;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void instantiateTestDatabase() {
        insertAdmin();

        int quantity = 200;
        Random rand = new Random();

        List<User> userList = new ArrayList<>();
        List<Log> logs = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        List<Origins> origins = new ArrayList<>();


        for (int i = 0; i < quantity; i++) {
            Events event;
            Log log;

            User user = User.builder()
                    .email("user_" + i + "@commomuser.com")
                    .password("@" + "user_" + i)
                    .origins(new ArrayList<>())
                    .grants(new HashSet<>(Collections.singletonList(TypeUser.COMMON_USER)))
                    .build();


            Origins originsEnt;

            if (rand.nextBoolean()) {
                originsEnt = Services.builderServico()
                        .name("Nome do Servico " + i)
                        .description("Descrição do Serviço " + i)
                        .password("123456")
                        .active(true)
                        .originKey(UUID.randomUUID().toString().replace("-", ""))
                        .grants(new HashSet<>(Collections.singleton(TypeUser.UNDEFINED)))
                        .build();
            } else {
                originsEnt = Systems.builderSistema()
                        .name("Nome do Sistema " + i)
                        .description("Descrição do Sistema " + i)
                        .password("123456")
                        .active(true)
                        .originKey(UUID.randomUUID().toString().replace("-", ""))
                        .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
                        .build();
            }

            userList.add(userRepositorie.save(user));
            origins.add(originsRepositorie.save(originsEnt));

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

                events.add(eventsRepositorie.save(event));

            }
            OriginUser originUser = OriginUser.builder()
                    .id(OriginUserPk.builder()
                            .user(userList.get(rand.nextInt(userList.size())))
                            .origins(origins.get(rand.nextInt(origins.size())))
                            .build())
                    .build();
            originUserRepositorie.save(originUser);
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
