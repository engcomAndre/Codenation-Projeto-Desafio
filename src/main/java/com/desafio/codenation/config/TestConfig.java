package com.desafio.codenation.config;

import com.desafio.codenation.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;

@Configuration
@Profile(TESTING)
public class TestConfig {

    private final DBService dbService;

    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantianteDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }

}
