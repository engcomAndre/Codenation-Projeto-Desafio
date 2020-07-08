package com.desafio.codenation.testconfiguration.servicetestconfig;

import com.desafio.codenation.services.SystemsService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;

@Profile(TESTING)
@Configuration
public class SystemsServiceUnitTestsConfiguration {

    @Bean
    @Primary
    public SystemsService systemsService() {
        return Mockito.mock(SystemsService.class);
    }
}
