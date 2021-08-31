package com.example.backend2lab1.risk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskAssesmentDto {

    private final boolean isPass;

    @JsonCreator
    public RiskAssesmentDto(@JsonProperty("pass") boolean isPass) {
        this.isPass = isPass;
    }

    public boolean isPass() {
        return isPass;
    }
}
