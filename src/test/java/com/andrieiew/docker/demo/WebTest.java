package com.andrieiew.docker.demo;

import com.andrieiew.docker.demo.users.User;
import com.andrieiew.docker.demo.users.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    UserService userService;

    private List<User> users = new LinkedList<>();

    @BeforeEach
    public void beforeEach() {
        userService.clear();
        User firstUser = User.builder()
                .username("firstUser")
                .firstName("First")
                .lastName("User")
                .build();
        User secondUser = User.builder()
                .username("secondUser")
                .firstName("Second")
                .lastName("User")
                .build();
        userService.save(firstUser);
        userService.save(secondUser);
        users.add(firstUser);
        users.add(secondUser);
    }

    @Test
    public void testAddNewUser() {

    }

    @Test
    public void testGetAllUsers() {
        HttpEntity<String> entity = new HttpEntity<>("", new HttpHeaders());
        String url = "http://localhost:" + port + "/getAllUsers";
        ResponseEntity<List> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, entity, List.class);
        List response = responseEntity.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test
    public void testPostUser() {
        Gson gson = new Gson();
        User newUser = User.builder()
                .username("NewUser")
                .firstName("New")
                .lastName("VeryNew")
                .build();
        HttpEntity<String> entity = new HttpEntity(newUser, new HttpHeaders());
        String url = "http://localhost:" + port + "/addNewUser";
        ResponseEntity responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, entity, Object.class);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        List<User> users = userService.findAll();
        Assertions.assertEquals(3, users.size());
        User newUserInDataBase = userService.findByUserName("NewUser").get();
        Assertions.assertNotNull(newUserInDataBase);
        Assertions.assertEquals(newUser, newUserInDataBase);

    }
}
