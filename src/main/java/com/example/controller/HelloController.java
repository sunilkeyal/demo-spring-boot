package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/test")
	public String index() {
		System.out.println("Hello Sunil");
		return "Greetings";
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("Hello Sunil");
		return "Hello Sunil";
	}
}
