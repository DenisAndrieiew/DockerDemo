package com.andrieiew.docker.demo.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(UserService.class)
@SpringBootTest
class UserTest {
    @Autowired
    private UserService userService;

    private final static String FIRST_USER_USERNAME = "firstTestUser";

    @BeforeEach
    public void beforeEach() {
        userService.clear();
        User firstTestUser = User.builder()
                .username(FIRST_USER_USERNAME)
                .firstName("first")
                .lastName("Test")
                .build();
        userService.save(firstTestUser);
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(Optional.empty(), userService.findByUserName("notExistUser"));
        Optional<User> test;
        test = userService.findByUserName(FIRST_USER_USERNAME);
        if (test.isEmpty()) {
            Assertions.fail("test user is empty");
        }
        Assertions.assertEquals(FIRST_USER_USERNAME, test.get().getUsername());
    }

    @Test
    void testSaveUser() {
        User secondTestUser = User.builder()
                .username("SecondTestUser")
                .firstName("Second")
                .lastName("User")
                .build();
        userService.save(secondTestUser);
        List<User> users = userService.findAll();
        System.out.println("users = " + users);
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(secondTestUser, userService.findByUserName("SecondTestUser").get());
    }
}