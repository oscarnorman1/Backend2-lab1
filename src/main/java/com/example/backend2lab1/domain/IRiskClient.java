package com.example.backend2lab1.domain;

import org.springframework.stereotype.Component;

@Component
public interface IRiskClient {
    boolean validate(String name);
}
