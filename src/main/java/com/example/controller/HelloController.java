package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Slf4j
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		String random = "Hello World " + Math.random();
		log.info(random);
		return random;
	}
}
