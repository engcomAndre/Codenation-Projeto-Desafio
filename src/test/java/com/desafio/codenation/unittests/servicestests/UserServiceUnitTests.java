package com.desafio.codenation.unittests.servicestests;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.services.UserService;
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
import java.util.HashSet;

import static com.desafio.codenation.constants.EnvironmentConstants.TESTING;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(TESTING)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceUnitTests {


    public static final User TEST_USER = User.builder()
            .id(1L)
            .email("admin@admin.com")
            .password("@admin")
            .grants(new HashSet<>(Arrays.asList(TypeUser.ADMIN, TypeUser.COMMON_USER)))
            .createdAt(LocalDateTime.now())
            .build();

    public static final User TEST_USER2 = User.builder()
            .email("admin@admin.com")
            .password("@admin")
            .grants(new HashSet<>(Arrays.asList(TypeUser.ADMIN, TypeUser.COMMON_USER)))
            .build();

    @Autowired
    private UserService userService;

    @Test
    public void whenIdIsProvided_thenRetrievalUserIsCorrect() {
        when(userService.getUser(1L)).thenReturn(TEST_USER);
        User retrievalUser = userService.getUser(1L);

        Assert.assertNotNull(retrievalUser);
        Assert.assertEquals(retrievalUser.getId(), TEST_USER.getId());
        Assert.assertEquals(retrievalUser.getEmail(), TEST_USER.getEmail());
        Assert.assertEquals(retrievalUser.getPassword(), TEST_USER.getPassword());
        Assert.assertEquals(retrievalUser.getGrants(), TEST_USER.getGrants());
    }

    @Test
    public void whenGetUsersMethod_thenRetrievalPageOfUsers() {
        PageImpl<User> pageUsers = new PageImpl<>(Arrays.asList(TEST_USER, TEST_USER));
        when(userService.getUsers(null, null)).thenReturn(pageUsers);
        Page<User> retrievalPageUsers = userService.getUsers(null, null);

        Assert.assertNotNull(retrievalPageUsers);
        Assert.assertEquals(retrievalPageUsers.getTotalElements(), pageUsers.getTotalElements());
        Assert.assertEquals(retrievalPageUsers.get().findFirst(), pageUsers.get().findFirst());

    }

    @Test
    public void whenInsertUserMethod_thenRetrievalUserWithIdAndCreatedData() {
        when(userService.insert(TEST_USER2)).thenReturn(TEST_USER);
        User retrievalUser = userService.insert(TEST_USER2);

        Assert.assertNotNull(retrievalUser);
        Assert.assertEquals(retrievalUser,TEST_USER);
        Assert.assertNotNull(retrievalUser.getCreatedAt());
        Assert.assertEquals(retrievalUser.getEmail(), TEST_USER.getEmail());
        Assert.assertEquals(retrievalUser.getPassword(), TEST_USER.getPassword());
        Assert.assertEquals(retrievalUser.getCreatedAt(), TEST_USER.getCreatedAt());
        Assert.assertEquals(retrievalUser.getGrants(), TEST_USER.getGrants());
    }

    @Test
    public void whenUpdateUserMethod_thenRetrievalUserNotEqualsUser() {
        when(userService.updateUser(1L, TEST_USER2)).thenReturn(TEST_USER);

        User retrievalUser = userService.updateUser(1L, TEST_USER2);

        Assert.assertNotNull(retrievalUser);
        Assert.assertNotEquals(TEST_USER,TEST_USER2);
    }

    @Test
    public void whenDeleteUserMethod_thenNotRetrievalAnything() {
        doNothing().when(userService).deleteUser(1L);
        verify(userService,times(0)).deleteUser(1L);
    }


}
