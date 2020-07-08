package com.desafio.codenation.unittests.servicestests;

import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.services.OriginService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(TESTING)
@RunWith(SpringJUnit4ClassRunner.class)
public class OriginsServiceUnitTests {


    public static final Origins TEST_ORIGIN = new Origins();

    public static final Origins TEST_ORIGIN2 = new Origins();

    @Autowired
    private OriginService originService;

    @Test
    public void whenIdAndIsProvided_thenRetrievalUserIsCorrect() {
        TEST_ORIGIN.setOriginKey("chave da origin");
        when(originService.findByIdAndAndOriginKeyAndAtivo(1L, TEST_ORIGIN.getOriginKey())).thenReturn(TEST_ORIGIN);
        Origins retrievaOrigins = originService.findByIdAndAndOriginKeyAndAtivo(1L, TEST_ORIGIN.getOriginKey());

        Assert.assertNotNull(retrievaOrigins);
        Assert.assertEquals(retrievaOrigins, TEST_ORIGIN);
    }


}
