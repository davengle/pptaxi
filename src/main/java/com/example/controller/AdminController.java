package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.service.RouteServiceImpl;
import com.example.service.RouteTimeTypeService;
import com.example.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RouteServiceImpl routeService;

	@Autowired
	private RouteTimeTypeService routeTimeService;

	@RequestMapping("/options")
	public String adminOptions() {
		return "admin/adminOptions";
	}

	@RequestMapping("/manageUsers")
	public String manageUsers(Model model) {

		model.addAttribute("user", new User());
		return "admin/manageUsers";
	}

	
	@RequestMapping("/user/retrieve")
	public String retrieve(String email, Model model) {
		model.addAttribute("user", userService.findByEmail(email));
		return "admin/manageUsers";
	}

	@PostMapping(value = "/user/save")
	public String save(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/manageUsers";
		} else {
			userService.save(user);
			return "admin/user/userConfirm";
		}
	}
	
	
	@RequestMapping("/manageRoutes")
	public String manageRoutes(Model model) {
		model.addAttribute("routeTimes", routeTimeService.findAll());
		model.addAttribute("route", new Route());
		model.addAttribute("quantity", new Integer(1));
		return "admin/manageRoutes";
	}


	@RequestMapping(value = "/route/save")
	public String routeSave(@Valid Route route, BindingResult bindingResult, Model model, HttpServletResponse response) throws IOException{

		Integer routeEntryQuantity = route.getQuantity();

		if (bindingResult.hasErrors()) {
			return "admin/manageRoutes";
		} else {
			while (routeEntryQuantity > 0) {
				Route innerRoute = new Route(route.getRouteDate());
				innerRoute.setStartTime(route.getStartTime());
				routeService.save(innerRoute);
				routeEntryQuantity--;
			}
			
			
			return "admin/route/routeConfirm";

		}
	}

	@RequestMapping(value = "/route/view")
	public String routeView(@Valid Route route, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/manageRoutes";
		} else {
			List<Route> currentRoutes = routeService.findAllByRouteDate(route.getRouteDate());
			model.addAttribute("routeTimes", routeTimeService.findAll());
			model.addAttribute("route", new Route());
			model.addAttribute("quantity", new Integer(1));
			model.addAttribute("currentRoutes", currentRoutes);
			return "admin/manageRoutes";
		}
	}

}
