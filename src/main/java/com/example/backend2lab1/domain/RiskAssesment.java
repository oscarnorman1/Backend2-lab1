package com.example.backend2lab1.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskAssesment {

    private final boolean isPass;

    @JsonCreator
    public RiskAssesment(@JsonProperty("pass") boolean isPass) {
        this.isPass = isPass;
    }

    public boolean isPass() {
        return isPass;
    }
}
