package com.andrieiew.docker.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class ApiService {
    @Value("${api.key}")
    String apiKey;

    @PostConstruct
    public void init() {
        log.info("apiService initialized");
        log.info("api key = {}", apiKey);
    }


}
