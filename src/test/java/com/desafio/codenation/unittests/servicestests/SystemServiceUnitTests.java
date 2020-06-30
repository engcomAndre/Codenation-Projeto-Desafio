package com.desafio.codenation.unittests.servicestests;

import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.services.SystemsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(TESTING)
@RunWith(SpringJUnit4ClassRunner.class)
public class SystemServiceUnitTests {


    public static final Systems TEST_SYSTEM = Systems.builderSistema()
            .id(1L)
            .name("Nome do Sistema ")
            .description("Descrição do Sistema ")
            .password("123456")
            .createdAt(LocalDateTime.now())
            .active(true)
            .originKey(UUID.randomUUID().toString().replace("-", ""))
            .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
            .build();

    public static final Systems TEST_SYSTEM2 = Systems.builderSistema()
            .name("Nome do Sistema 2 ")
            .description("Descrição do Sistema 2")
            .password("123456")
            .active(true)
            .originKey(UUID.randomUUID().toString().replace("-", ""))
            .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
            .build();

    @Autowired
    private SystemsService systemsService;

    @Test
    public void whenIdIsProvided_thenRetrievalUserIsCorrect() {
        when(systemsService.getSistema(1L)).thenReturn(TEST_SYSTEM);
        Systems retrievaSystems = systemsService.getSistema(1L);

        Assert.assertNotNull(retrievaSystems);
        Assert.assertEquals(retrievaSystems.getId(), TEST_SYSTEM.getId());
        Assert.assertEquals(retrievaSystems.getDescription(), TEST_SYSTEM.getDescription());
        Assert.assertEquals(retrievaSystems.getOriginKey(), TEST_SYSTEM.getOriginKey());
        Assert.assertEquals(retrievaSystems.getGrants(), TEST_SYSTEM.getGrants());
        Assert.assertEquals(true, TEST_SYSTEM.getActive());
    }

    @Test
    public void whenGetUsersMethod_thenRetrievalPageOfUsers() {
        PageImpl<Systems> pageSystems = new PageImpl<>(Arrays.asList(TEST_SYSTEM, TEST_SYSTEM));
        when(systemsService.getSistemas(null, null)).thenReturn(pageSystems);
        Page<Systems> retrievalPageSystems = systemsService.getSistemas(null, null);

        Assert.assertNotNull(retrievalPageSystems);
        Assert.assertEquals(retrievalPageSystems.getTotalElements(), pageSystems.getTotalElements());
        Assert.assertEquals(retrievalPageSystems.get().findFirst(), pageSystems.get().findFirst());

    }

    @Test
    public void whenInsertUserMethod_thenRetrievalUserWithIdAndCreatedData() {
        when(systemsService.insert(TEST_SYSTEM2)).thenReturn(TEST_SYSTEM);
        Systems retrievaSystems = systemsService.insert(TEST_SYSTEM2);

        Assert.assertNotNull(retrievaSystems);
        Assert.assertEquals(retrievaSystems, TEST_SYSTEM);
        Assert.assertNotNull(retrievaSystems.getCreatedAt());
        Assert.assertEquals(retrievaSystems.getDescription(), TEST_SYSTEM.getDescription());
        Assert.assertEquals(retrievaSystems.getOriginKey(), TEST_SYSTEM.getOriginKey());
        Assert.assertEquals(retrievaSystems.getPassword(), TEST_SYSTEM.getPassword());
        Assert.assertEquals(retrievaSystems.getCreatedAt(), TEST_SYSTEM.getCreatedAt());
        Assert.assertEquals(retrievaSystems.getGrants(), TEST_SYSTEM.getGrants());
    }

    @Test
    public void whenUpdateUserMethod_thenRetrievalUserNotEqualsUser() {
        doNothing().when(systemsService).updateSistema(1L, TEST_SYSTEM2);
        verify(systemsService, times(0)).updateSistema(1L, TEST_SYSTEM2);
    }

    @Test
    public void whenDeleteUserMethod_thenNotRetrievalAnything() {
        doNothing().when(systemsService).deleteSistema(1L);
        verify(systemsService, times(0)).deleteSistema(1L);
    }


}
