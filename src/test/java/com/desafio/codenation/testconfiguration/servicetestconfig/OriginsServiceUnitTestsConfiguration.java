package com.desafio.codenation.testconfiguration.servicetestconfig;

import com.desafio.codenation.services.OriginService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;

@Profile(TESTING)
@Configuration
public class OriginsServiceUnitTestsConfiguration {

    @Bean
    @Primary
    public OriginService originService() {
        return Mockito.mock(OriginService.class);
    }
}
