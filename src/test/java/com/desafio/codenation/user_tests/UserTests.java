//package com.desafio.codenation.user_tests;
//
//import com.desafio.codenation.domain.user.User;
//import com.desafio.codenation.domain.user.enums.TypeUser;
//import com.desafio.codenation.repositories.UserRepositorie;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserTests {
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private UserRepositorie userRepositorie;
//
//    @Test
//    public void test(){
//        User testUser = User.builder()
//                .email("test@test.com")
//                .password("123456")
//                .grants(new HashSet<TypeUser>(TypeUser.ADMIN,TypeUser.COMMON_USER))
//                .build();
//
//
//
//
//
//    }
//
//
//
//}
