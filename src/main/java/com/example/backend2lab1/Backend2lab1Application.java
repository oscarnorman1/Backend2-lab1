package com.example.backend2lab1;

import com.example.backend2lab1.domain.IRiskClient;
import com.example.backend2lab1.risk.RiskAssessment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Backend2lab1Application {

    @Value("${risk.service-url}")
    public String host;

    public static void main(String[] args) {
        SpringApplication.run(Backend2lab1Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRiskClient riskClient(RestTemplate restTemplate) {
        return new RiskAssessment(restTemplate, host);
    }

}
