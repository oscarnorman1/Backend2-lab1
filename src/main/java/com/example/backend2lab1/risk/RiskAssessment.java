package com.example.backend2lab1.risk;


import com.example.backend2lab1.domain.IRiskClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class RiskAssessment implements IRiskClient {

    private final RestTemplate restTemplate;
    private final String baseURL;

    public RiskAssessment(RestTemplate restTemplate, String baseURL) {
        this.restTemplate = restTemplate;
        this.baseURL = baseURL;
    }

    @Override
    public boolean validate(String name) {
        return this.restTemplate.getForEntity(baseURL + "/risk/" + name, RiskAssesmentDto.class)
                .getBody().isPass();
    }
}
