package com.example.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void testFeignClient() {
        System.out.println("testing feign client");
    }
}
