package com.desafio.codenation.testconfiguration;

import com.desafio.codenation.services.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;

@Profile(TESTING)
@Configuration
public class UserServiceUnitTestsConfiguration {

    @Bean
    @Primary
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
