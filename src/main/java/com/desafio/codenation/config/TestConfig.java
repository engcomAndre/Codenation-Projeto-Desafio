package com.desafio.codenation.config;

import com.desafio.codenation.services.DBService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantianteDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

}
