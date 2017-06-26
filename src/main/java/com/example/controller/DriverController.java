package com.example.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@RequestMapping("/viewRoutes")
	public String modifyRoutes(Model model, Principal principal){
		User currentUser = userService.findByEmail(principal.getName());
		List<Route> selectedRoutes = routeService.findAllByUser(currentUser);
		model.addAttribute("selectedRoutes", selectedRoutes);
		return "driver/viewRoutes";
	}
	
	@RequestMapping("/selectDate")
	public String selectDate(Model model, Principal principal, @ModelAttribute Route route){
		User unassignedUser = userService.findByEmail("unassigned");
		User currentUser = userService.findByEmail(principal.getName());
		if (null == route.getRouteDate()) {
			LocalDate today = LocalDate.now();
			route = new Route(today);
		}
		LocalDate routeDate = route.getRouteDate();
		Route returnedRoute = new Route(route.getRouteDate());
		List<Route> selectedRoutes = routeService.findAllByUser(currentUser);
		List<Route> startTimeAvailableRoutes = routeService.findDistinctStartTimesByRouteDateAndUser(routeDate, unassignedUser);
		model.addAttribute("route", returnedRoute);
		model.addAttribute("availableRouteTimes", startTimeAvailableRoutes);
		model.addAttribute("selectedRoutes", selectedRoutes);
		return "driver/addRoutes";
	}
	
	@RequestMapping("/addRoutes")
	public String assignRoutes(Model model, Principal principal, @ModelAttribute Route route) {
		User unassignedUser = userService.findByEmail("unassigned");
		User currentUser = userService.findByEmail(principal.getName());
		Route innerRoute = routeService.findFirstByRouteDateAndUserAndStartTime(route.getRouteDate(), unassignedUser, route.getStartTime());
		routeService.assignRoute(currentUser, innerRoute);
		routeService.save(innerRoute);
		LocalDate routeDate = route.getRouteDate();
		List<Route> selectedRoutes = routeService.findAllByUser(currentUser);
		List<Route> startTimeAvailableRoutes = routeService.findDistinctStartTimesByRouteDateAndUser(routeDate, unassignedUser);
		model.addAttribute("route", new Route(route.getRouteDate()));
		model.addAttribute("availableRouteTimes", startTimeAvailableRoutes);
		model.addAttribute("selectedRoutes", selectedRoutes);
		return "driver/addRoutes";
	}
	
	@RequestMapping("/route/remove/{id}")
	public String deleteRoute(Model model, Principal principal, @PathVariable Long id) {
		User unassignedUser = userService.findByEmail("unassigned");
		Route route = routeService.findOne(id);
		routeService.assignRoute(unassignedUser, route);
		routeService.save(route);
		return "redirect:/driver/viewRoutes";
	}
	

	
	
}
