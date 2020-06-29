package com.desafio.codenation.config;

import com.desafio.codenation.services.DBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.DEVELOPMENT;

@Configuration
@Profile(DEVELOPMENT)
public class DevConfig {

    private final DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String stratetgy;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instateateDatabase() {
        if (!"create".equals(stratetgy)) {
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

}
