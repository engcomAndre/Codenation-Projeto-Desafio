package com.desafio.codenation.unittests.resourcestests;

import com.desafio.codenation.domain.user.DTO.NewUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.testconfiguration.resourcestestconfig.PageableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;
import static junit.framework.TestCase.assertEquals;


@SpringBootTest
@ActiveProfiles(TESTING)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserResourcesTest extends com.desafio.codenation.unittests.resourcestests.resourcestestconfig.AbstractTest {
    public NewUserDTO newUserDTO = NewUserDTO.builder()
            .email("test@test.com")
            .password("testando")
            .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
            .build();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void whenIdIsNotProvided_RetrievalListOfUsersAndResponseStatus200() throws Exception {
        String uri = "/user";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        PageableResponse<UserDTO> pageUserDtos = super.mapFromJson(content, PageableResponse.class);

        Assert.assertNotNull(pageUserDtos);
        Assert.assertFalse(pageUserDtos.isEmpty());
    }

    @Test
    public void whenIdIsProvided_RetrievalUserAndResponseStatus200() throws Exception {
        String uri = "/user/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        Map<String, String> contentMap = super.mapFromJson(content, Map.class);

        Assert.assertTrue(contentMap.containsKey("id"));
        Assert.assertTrue(contentMap.containsKey("email"));
        Assert.assertTrue(contentMap.containsKey("grants"));
        Assert.assertTrue(contentMap.containsKey("createdAt"));
        Assert.assertTrue(contentMap.containsKey("modifiedAt"));
    }

    @Test
    public void whenIdNotExitsIsProvided_NotFoundMessageInResponseAndResponseStatus404() throws Exception {
        String uri = "/user/-1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);

        String content = mvcResult.getResponse().getContentAsString();

        Map contentMap = super.mapFromJson(content, Map.class);

        Assert.assertTrue(contentMap.containsKey("status"));
        Assert.assertTrue(contentMap.containsKey("msg"));
        Assert.assertTrue(contentMap.containsKey("timeStamp"));

    }

    @Test
    public void whenNewUserIsValid_RetrieveUriToResourcesAndResponseStatus201() throws Exception {
        String uri = "/user";

        String newUser = super.mapToJson(newUserDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newUser))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(201, status);

        String content = mvcResult.getResponse().getContentAsString();
        String contentHeader = mvcResult.getResponse().getHeader("location");

        Assert.assertTrue(content.isEmpty());
        Assert.assertNotNull(contentHeader);
        Assert.assertFalse(contentHeader.isEmpty());

    }

    @Test
    public void whenNewUserHasInvalidField_RetrieveErrorResponseAndResponseStatus400() throws Exception {
        String uri = "/user";
        newUserDTO.setEmail("emailinvalido");
        String newUser = super.mapToJson(newUserDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newUser))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();

        Map contentMap = super.mapFromJson(content, Map.class);
        String contentHeader = mvcResult.getResponse().getHeader("location");

        Assert.assertFalse(content.isEmpty());
        Assert.assertNull(contentHeader);
        Assert.assertTrue(contentMap.containsKey("status"));
        Assert.assertTrue(contentMap.containsKey("msg"));
        Assert.assertTrue(contentMap.containsKey("errors"));

    }

    @Test
    public void whenUserIsUpdated_RetrieveUserAreModifiedAndResponseStatus200() throws Exception {
        String uri = "/user/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        Map contentMapOld = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);

        NewUserDTO newUserDTO = NewUserDTO.builder()
                .email("novoemail@email.com")
                .grants(new HashSet<>(Collections.singletonList(TypeUser.UNDEFINED)))
                .password("123456789")
                .build();

        String newUserString = super.mapToJson(newUserDTO);

        MvcResult mvcResultUpdate = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newUserString))
                .andReturn();

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Map contentMapUpdated = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);

        Assert.assertNotNull(contentMapUpdated);
        Assert.assertSame(contentMapOld.get("id"), contentMapUpdated.get("id"));
        Assert.assertEquals(contentMapOld.get("createdAt"), contentMapUpdated.get("createdAt"));
        Assert.assertNotSame(contentMapOld, contentMapUpdated);
        Assert.assertNotSame(contentMapOld.get("email"), contentMapUpdated.get("email"));
        Assert.assertNotSame(contentMapOld.get("grants"), contentMapUpdated.get("grants"));
        Assert.assertNotSame(contentMapOld.get("modifiedAt"), contentMapUpdated.get("modifiedAt"));
    }

    @Test
    public void whenDeleteUserById_RetrievalNotFoundUserResponseAndResponseStatus() throws Exception {
        String uri = "/user/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assert.assertEquals(204, mvcResult.getResponse().getStatus());

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assert.assertEquals(404, mvcResult.getResponse().getStatus());

        Map contentMap = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);

        Assert.assertFalse(contentMap.isEmpty());
        Assert.assertTrue(contentMap.containsKey("status"));
        Assert.assertTrue(contentMap.containsKey("msg"));
        Assert.assertTrue(contentMap.containsKey("timeStamp"));
    }
}
