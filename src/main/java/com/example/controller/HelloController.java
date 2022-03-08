package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@RestController
@Slf4j
public class HelloController {

	@RequestMapping("/test")
	public String index() {
		log.info("Hello World test metbod " + Math.random());
		return "Greetings";
	}

	@RequestMapping("/hello")
	public String hello() {
		log.info("Hello World hello mwthod " + Math.random());
		return "Hello Sunil";
	}
}
