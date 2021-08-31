package com.example.backend2lab1.domain;

import org.springframework.stereotype.Service;

@Service
public interface IRiskClient {
    boolean validate(String name);
}
