package com.desafio.codenation.config;

import com.desafio.codenation.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantianteDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

}
