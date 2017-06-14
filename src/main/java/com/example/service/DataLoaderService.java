package com.example.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.enums.UserRole;
import com.example.repositories.RouteRepository;

@Service
public class DataLoaderService {

	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private UserServiceImpl userService;
	
	
	public DataLoaderService() {
	
	}
		
//	@Autowired
//	public DataLoaderService(RouteRepository routeRepository, UserRepository userRepository) {
//		this.routeRepository = routeRepository;
//		this.userRepository = userRepository;
//	}
	
	
	@PostConstruct
	private void loadData(){
		
	
		
		Route route1 = new Route(new Date(), new Date());
		routeRepository.save(route1);

		Route route2 = new Route(new Date(), new Date());
		routeRepository.save(route2);
		
		User user1 = new User("jonny@gmail.com", "password");
		user1.setFirstName("Jonny");
		user1.setLastName("Poe");
		user1.setRole(UserRole.ROLE_ADMIN);
		user1.setStatus(true);
		userService.save(user1);

		User user2 = new User("scott@gmail.com", "password");
		user2.setFirstName("Scott");
		user2.setLastName("Balwinski");
		user2.setRole(UserRole.ROLE_USER);
		user2.setStatus(true);
		userService.save(user2);
		
		
	}
	
}
