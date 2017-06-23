package com.example.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.service.RouteService;
import com.example.service.UserService;

@Controller
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping("/modifyRoutes")
	public String modifyRoutes(Model model, Principal principal){
		User unassignedUser = userService.findByEmail("unassigned");
		User currentUser = userService.findByEmail(principal.getName());
		LocalDate today = LocalDate.now();
		Route route = new Route(today);
		List<Route> selectedRoutes = routeService.findAllByUser(currentUser);
		List<Route> startTimeAvailableRoutes = routeService.findDistinctStartTimesByRouteDateAndUser(today, unassignedUser);
		model.addAttribute("availableRouteTimes", startTimeAvailableRoutes);
		model.addAttribute("selectedRoutes", selectedRoutes);
		model.addAttribute("route", route);
		return "driver";
	}
	
	@RequestMapping("/selectDate")
	public String selectDate(Model model, Principal principal, @ModelAttribute Route route){
		User unassignedUser = userService.findByEmail("unassigned");
		User currentUser = userService.findByEmail(principal.getName());
		LocalDate routeDate = route.getRouteDate();
		Route returnedRoute = new Route(route.getRouteDate());
		List<Route> selectedRoutes = routeService.findAllByUser(currentUser);
		List<Route> startTimeAvailableRoutes = routeService.findDistinctStartTimesByRouteDateAndUser(routeDate, unassignedUser);
		model.addAttribute("route", returnedRoute);
		model.addAttribute("availableRouteTimes", startTimeAvailableRoutes);
		model.addAttribute("selectedRoutes", selectedRoutes);
		return "driver";
	}
	
	@RequestMapping("/assignRoutes")
	public String assignRoutes(Model model, Principal principal, @ModelAttribute Route route) {
		User unassignedUser = userService.findByEmail("unassigned");
		User currentUser = userService.findByEmail(principal.getName());
		currentUser.addRoute(route);
		userService.save(currentUser);
		LocalDate routeDate = route.getRouteDate();
		List<Route> selectedRoutes = routeService.findAllByUser(currentUser);
		List<Route> startTimeAvailableRoutes = routeService.findDistinctStartTimesByRouteDateAndUser(routeDate, unassignedUser);
		model.addAttribute("route", new Route(routeDate));
		model.addAttribute("availableRouteTimes", startTimeAvailableRoutes);
		model.addAttribute("selectedRoutes", selectedRoutes);
		return "driver";
	}
	

	
	
}
