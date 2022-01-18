package com.andrieiew.docker.demo;

import com.andrieiew.docker.demo.controller.MainController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private MainController controller;

    @Test
    public void contextLoadTest() {
        Assertions.assertNotNull(controller);
    }
}
