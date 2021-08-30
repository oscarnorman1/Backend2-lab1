package com.example.backend2lab1.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.client.RestTemplate;

public class Adapter {

    private RestTemplate restTemplate;
    private String baseURL = "http://localhost:8082/risk";

}
