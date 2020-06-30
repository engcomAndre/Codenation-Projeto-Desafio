package com.desafio.codenation.testconfiguration;

import com.desafio.codenation.services.ServicesService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;

@Profile(TESTING)
@Configuration
public class ServicesServiceUnitTestsConfiguration {

    @Bean
    @Primary
    public ServicesService servicesService() {
        return Mockito.mock(ServicesService.class);
    }
}
