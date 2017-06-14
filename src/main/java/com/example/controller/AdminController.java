package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.entity.User;
import com.example.repositories.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/options")
	public String adminOptions() {
		return "admin/adminOptions";
	}

	@RequestMapping("/manageUsers")
	public String manageUsers(Model model) {

		model.addAttribute("user", new User());
		return "admin/manageUsers";
	}

	@RequestMapping("/manageRoutes")
	public String manageRoutes() {
		return "admin/manageRoutes";
	}

	@RequestMapping("/user/retrieve")
	public String retrieve(String email, Model model) {
		model.addAttribute("user", userRepository.findByEmail(email));
		return "admin/manageUsers";
	}

	@PostMapping(value = "/user/save")
	public String save(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/manageUsers";
		} else {
			userRepository.save(user);
			return "admin/user/userConfirm";
		}
	}

}
