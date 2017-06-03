package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	
	
	@RequestMapping("/view")
	public String view(){
		return "guests view...";
	}
	
	@RequestMapping("/select")
	public String selectRoute(){
		return "select route...";
	}
	

	
	
}
