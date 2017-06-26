package com.example.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.service.RouteTimeTypeService;
import com.example.service.impl.RouteServiceImpl;
import com.example.service.impl.UserServiceImpl;

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

	@RequestMapping("/user/viewUsers")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/user/viewUsers";
	}

	@RequestMapping("user/create")
	public String createNewUser(Model model) {
		model.addAttribute("user", new User());
		return "admin/user/createUserForm";
	}
	
	@RequestMapping("/user/edit/{id}")
	public String userEdit(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		return "admin/user/modifyUserForm";
	}
	
	@RequestMapping("/user/show/{id}")
	public String retrieve(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		return "admin/user/showUser";
	}

	@PostMapping(value = "/user/save")
	public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/user/createUserForm";
		} else {
			userService.save(user);
			return	"redirect:/admin/user/viewUsers";
		}
	}
	
	@PostMapping(value = "/user/update")
	public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/user/modifyUserForm";
		} else {
			userService.save(user);
			return	"redirect:/admin/user/viewUsers";
		}
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
			
			model.addAttribute("routeTimes", routeTimeService.findAll());
			model.addAttribute("route", route);
			model.addAttribute("allDailyRoutes", routeService.findAllByRouteDate(route.getRouteDate()));
			model.addAttribute("quantity", new Integer(1));
			
			return "admin/route/add";

		}
	}

	@RequestMapping(value = "/route/view")
	public String routeView(Route route, BindingResult bindingResult, Model model) {
			if (null == route.getRouteDate()) {
				route.setRouteDate(LocalDate.now());;
			}
			model.addAttribute("allDailyRoutes", routeService.findAllByRouteDate(route.getRouteDate()));
			return "admin/route/view";
	}
	
	@RequestMapping(value = "/route/add")
	public String routeAdd(Route route, BindingResult bindingResult, Model model) {
			if (null == route.getRouteDate()) {
				route.setRouteDate(LocalDate.now());;
			}
			model.addAttribute("routeTimes", routeTimeService.findAll());
			model.addAttribute("allDailyRoutes", routeService.findAllByRouteDate(route.getRouteDate()));
			return "admin/route/add";
	}

	@RequestMapping("/route/remove/{id}")
	public String deleteRoute(Model model, @PathVariable Long id) {
		routeService.delete(id);
		return "redirect:/admin/route/add";
	}
}
