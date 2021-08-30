package com.example.backend2lab1.domain;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class RiskAssesmentTest {


    @Test
    void derializeJsonTest() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskAssesment> out = restTemplate.getForEntity("http://localhost:8082/risk/dan", RiskAssesment.class);

        assertEquals(out.getBody().isPass(), false);
    }

    @Test
    void

}