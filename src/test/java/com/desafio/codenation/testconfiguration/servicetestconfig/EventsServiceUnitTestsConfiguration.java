package com.desafio.codenation.testconfiguration.servicetestconfig;

import com.desafio.codenation.services.EventsService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;

@Profile(TESTING)
@Configuration
public class EventsServiceUnitTestsConfiguration {

    @Bean
    @Primary
    public EventsService eventsService() {
        return Mockito.mock(EventsService.class);
    }
}
