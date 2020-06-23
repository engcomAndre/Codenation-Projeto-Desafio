package com.desafio.codenation.config;

import com.desafio.codenation.services.DBService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.text.ParseException;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DBService dbService;

    @Bean
    public DataSource dataSource() throws ParseException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);

        dbService.instantiateTestDatabase();

        return new HikariDataSource(config);
    }
}
