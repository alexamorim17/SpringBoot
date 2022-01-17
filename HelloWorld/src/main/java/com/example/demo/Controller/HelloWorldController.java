package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	@GetMapping
	public String hello() {
		return "========Principais Objetivos========\n1-Me aperfeiçoar e aprender mais sobre a nova tecnologia \n2-Manter a foco e a determinação \n 3-Fazer os exercícios com facilidade";
	}
}
