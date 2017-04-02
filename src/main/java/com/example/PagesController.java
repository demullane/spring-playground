package com.example;
	
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {

	@GetMapping("/")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/math/pi")
	public String getPI() { return "3.141592653589793"; }
}