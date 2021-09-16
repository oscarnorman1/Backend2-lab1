package com.example.backend2lab1.risk;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
import static org.junit.jupiter.api.Assertions.*;

class RiskAssessmentTest {

    RiskAssessment riskassessment;

    WireMockServer wireMockServer;

    @BeforeEach
    void init() {
        wireMockServer = new WireMockServer(9090);
        wireMockServer.start();

        riskassessment = new RiskAssessment(new RestTemplate(), wireMockServer.baseUrl());
    }

    @AfterEach
    void after() {
        wireMockServer.stop();
    }

    @Test
    void CanFetchAndDeserializeRiskAssessmentDto() {

        wireMockServer.stubFor(get(urlEqualTo("/risk/dan")).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("content-type", APPLICATION_JSON.toString())
                .withBody("{\"pass\": false}")));

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RiskAssesmentDto> test = restTemplate.getForEntity(wireMockServer.baseUrl() + "/risk/dan", RiskAssesmentDto.class);
        assertEquals(test.getStatusCode(), HttpStatus.OK);
        assertEquals(test.getBody().isPass(), false);
    }

    @Test
    void validateTest() {

        wireMockServer.stubFor(get(urlEqualTo("/risk/hejhej")).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("content-type", APPLICATION_JSON.toString())
                .withBody("{\"pass\": true}")));

        wireMockServer.stubFor(get(urlEqualTo("/risk/hej")).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("content-type", APPLICATION_JSON.toString())
                .withBody("{\"pass\": false}")));

        assertTrue(riskassessment.validate("hejhej"));
        assertFalse(riskassessment.validate("hej"));
    }

    @Test
    void canMakeRiskAssessment() {
        wireMockServer.stubFor(get(urlEqualTo("/risk/Leif")).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("content-type", APPLICATION_JSON.toString())
                .withBody("{\"pass\": true}")));

        final RiskAssessment riskAssessmentTest = new RiskAssessment(new RestTemplate(), wireMockServer.baseUrl());
        boolean test = riskAssessmentTest.validate("Leif");
        assertTrue(test);
    }
}