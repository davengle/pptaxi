package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.entity.Route;
import com.example.enums.RouteTimes;
import com.example.repositories.RouteRepository;

@Controller
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	RouteRepository routeRepository;

	
	@RequestMapping("/selectRoute")
	public String selectRoute(Model model){
		
		Iterable<Route> routes = routeRepository.findAll();
		
		model.addAttribute("routeTimes", routes);
		return "driver";
	}
	

	
	
}
