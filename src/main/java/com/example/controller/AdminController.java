package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@RequestMapping("/options")
	public String adminOptions(){
		return "admin/adminOptions";
	}
	
	
	@RequestMapping("/manageUsers")
	public String manageUsers() {
		return "admin/manageUsers";
	}
	
	@RequestMapping("/manageRoutes")
	public String manageRoutes() {
		return "admin/manageRoutes";
	}
}
