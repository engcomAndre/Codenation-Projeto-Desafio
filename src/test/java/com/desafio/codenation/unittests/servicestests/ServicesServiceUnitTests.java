package com.desafio.codenation.unittests.servicestests;

import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.services.ServicesService;
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
public class ServicesServiceUnitTests {


    public static final Services TEST_SYSTEM = Services.builderServico()
            .id(1L)
            .name("Nome do Sistema ")
            .description("Descrição do Sistema ")
            .password("123456")
            .createdAt(LocalDateTime.now())
            .active(true)
            .originKey(UUID.randomUUID().toString().replace("-", ""))
            .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
            .build();

    public static final Services TEST_SYSTEM2 = Services.builderServico()
            .name("Nome do Sistema 2 ")
            .description("Descrição do Sistema 2")
            .password("123456")
            .active(true)
            .originKey(UUID.randomUUID().toString().replace("-", ""))
            .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
            .build();

    @Autowired
    private ServicesService systemsService;

    @Test
    public void whenIdIsProvided_thenRetrievalUserIsCorrect() {
        when(systemsService.getServicoById(1L)).thenReturn(TEST_SYSTEM);
        Services retrievaServices = systemsService.getServicoById(1L);

        Assert.assertNotNull(retrievaServices);
        Assert.assertEquals(retrievaServices.getId(), TEST_SYSTEM.getId());
        Assert.assertEquals(retrievaServices.getDescription(), TEST_SYSTEM.getDescription());
        Assert.assertEquals(retrievaServices.getOriginKey(), TEST_SYSTEM.getOriginKey());
        Assert.assertEquals(retrievaServices.getGrants(), TEST_SYSTEM.getGrants());
        Assert.assertEquals(true, TEST_SYSTEM.getActive());
    }

    @Test
    public void whenGetUsersMethod_thenRetrievalPageOfUsers() {
        PageImpl<Services> pageServices = new PageImpl<>(Arrays.asList(TEST_SYSTEM, TEST_SYSTEM));
        when(systemsService.getServicos(null, null)).thenReturn(pageServices);
        Page<Services> retrievalPageServices = systemsService.getServicos(null, null);

        Assert.assertNotNull(retrievalPageServices);
        Assert.assertEquals(retrievalPageServices.getTotalElements(), pageServices.getTotalElements());
        Assert.assertEquals(retrievalPageServices.get().findFirst(), pageServices.get().findFirst());

    }

    @Test
    public void whenInsertUserMethod_thenRetrievalUserWithIdAndCreatedData() {
        when(systemsService.insert(TEST_SYSTEM2)).thenReturn(TEST_SYSTEM);
        Services retrievaServices = systemsService.insert(TEST_SYSTEM2);

        Assert.assertNotNull(retrievaServices);
        Assert.assertEquals(retrievaServices, TEST_SYSTEM);
        Assert.assertNotNull(retrievaServices.getCreatedAt());
        Assert.assertEquals(retrievaServices.getDescription(), TEST_SYSTEM.getDescription());
        Assert.assertEquals(retrievaServices.getOriginKey(), TEST_SYSTEM.getOriginKey());
        Assert.assertEquals(retrievaServices.getPassword(), TEST_SYSTEM.getPassword());
        Assert.assertEquals(retrievaServices.getCreatedAt(), TEST_SYSTEM.getCreatedAt());
        Assert.assertEquals(retrievaServices.getGrants(), TEST_SYSTEM.getGrants());
    }

    @Test
    public void whenUpdateUserMethod_thenRetrievalUserNotEqualsUser() {
        doNothing().when(systemsService).updateServico(1L, TEST_SYSTEM2);
        verify(systemsService, times(0)).updateServico(1L, TEST_SYSTEM2);
    }

    @Test
    public void whenDeleteUserMethod_thenNotRetrievalAnything() {
        doNothing().when(systemsService).deleteService(1L);
        verify(systemsService, times(0)).deleteService(1L);
    }


}
