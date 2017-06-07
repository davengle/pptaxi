package com.example.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Route;
import com.example.domain.User;
import com.example.repositories.RouteRepository;
import com.example.repositories.UserRepository;

@Service
public class DataLoaderService {

	private RouteRepository routeRepository;
	private UserRepository userRepository;
	
	
	public DataLoaderService() {
	
	}
		
	@Autowired
	public DataLoaderService(RouteRepository routeRepository, UserRepository userRepository){
		this.routeRepository = routeRepository;
		this.userRepository = userRepository;
	}
	
	
	@PostConstruct
	private void loadData(){
		
		Route route1 = new Route(new Date(), new Date());
		routeRepository.save(route1);
		
		Route route2 = new Route(new Date(), new Date());
		routeRepository.save(route2);
		
		User user1 = new User("user1@gmail.com", "password");
		userRepository.save(user1);
		
		User user2 = new User("user2@gmail.com", "password");
		userRepository.save(user2);
		
		
	}
	
}
