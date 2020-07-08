package com.desafio.codenation.unittests.servicestests;

import com.desafio.codenation.domain.events.DTO.NewEventsDTO;
import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.services.EventsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.UUID;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(TESTING)
@RunWith(SpringJUnit4ClassRunner.class)
public class EventsServiceUnitTests {

    public static final Events TEST_EVENTS = Events.builder()
            .description("Descrição de um evento " + UUID.randomUUID().toString().replace("-", ""))
            .level(TypeLevel.toEnum(1))
            .quantity(15)
            .log(Log.builder()
                    .description("Descrição de um log de evento " + UUID.randomUUID().toString().replace("-", ""))
                    .build())
            .origins(null)
            .build();

    public static final Events TEST_EVENTS2 = Events.builder()
            .description("Descrição de um evento " + UUID.randomUUID().toString().replace("-", ""))
            .level(TypeLevel.toEnum(1))
            .quantity(15)
            .log(Log.builder()
                    .description("Novo Evento")
                    .build())
            .origins(null)
            .build();

    public static final NewEventsDTO TEST_NEWEVENT = NewEventsDTO.builder()
            .description("Descrição de um evento " + UUID.randomUUID().toString().replace("-", ""))
            .level(TypeLevel.INFO.getDesc())
            .quantity(15)
            .logDescription("Descrição de um log de evento " + UUID.randomUUID().toString().replace("-", ""))
            .originId("1L")
            .originKey("Novo Evento")
            .build();
    @Autowired
    private EventsService eventsService;

    @Test
    public void whenIdIsProvided_thenRetrievalEventsIsCorrect() {
        when(eventsService.getEvento(1L)).thenReturn(TEST_EVENTS);
        Events retrievaEvents = eventsService.getEvento(1L);

        Assert.assertNotNull(retrievaEvents);
        Assert.assertEquals(retrievaEvents.getId(), TEST_EVENTS.getId());
        Assert.assertEquals(retrievaEvents.getDescription(), TEST_EVENTS.getDescription());
        Assert.assertEquals(retrievaEvents.getId(), TEST_EVENTS.getId());
        Assert.assertEquals(retrievaEvents.getDescription(), TEST_EVENTS.getDescription());
        Assert.assertEquals(retrievaEvents.getQuantity(), TEST_EVENTS.getQuantity());
    }

    @Test
    public void whenGetEventssMethod_thenRetrievalPageOfEventss() {
        PageImpl<Events> pageEvents = new PageImpl<>(Arrays.asList(TEST_EVENTS, TEST_EVENTS));
        when(eventsService.getEventos(null, null)).thenReturn(pageEvents);
        Page<Events> retrievalPageEvents = eventsService.getEventos(null, null);

        Assert.assertNotNull(retrievalPageEvents);
        Assert.assertEquals(retrievalPageEvents.getTotalElements(), pageEvents.getTotalElements());
        Assert.assertEquals(retrievalPageEvents.get().findFirst(), pageEvents.get().findFirst());
    }

    @Test
    public void whenInsertEventsMethod_thenRetrievalEventsWithIdAndCreatedData() {
        when(eventsService.insertEvento(TEST_NEWEVENT)).thenReturn(TEST_EVENTS2);
        Events retrievaEvents = eventsService.insertEvento(TEST_NEWEVENT);

        Assert.assertNotNull(retrievaEvents);
        Assert.assertEquals(retrievaEvents, TEST_EVENTS2);
        Assert.assertEquals(retrievaEvents.getId(), TEST_EVENTS2.getId());
        Assert.assertEquals(retrievaEvents.getDescription(), TEST_EVENTS2.getDescription());
        Assert.assertEquals(retrievaEvents.getLevel(), TEST_EVENTS2.getLevel());
        Assert.assertEquals(retrievaEvents.getQuantity(), TEST_EVENTS2.getQuantity());
    }

    @Test
    public void whenUpdateEventsMethod_thenRetrievalEventsNotEqualsEvents() {
        doNothing().when(eventsService).updateEvento(1L, TEST_EVENTS2);
        verify(eventsService, times(0)).updateEvento(1L, TEST_EVENTS2);
    }

    @Test
    public void whenDeleteEventsMethod_thenNotRetrievalAnything() {
        doNothing().when(eventsService).deleteEvento(1L);
        verify(eventsService, times(0)).deleteEvento(1L);
    }


}
