package com.example.service.impl;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.enums.UserRole;
import com.example.service.RouteService;
import com.example.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
		
	@Autowired
	UserService userService;
	
	@Autowired
	RouteService routeService;
	
	
	final static String USER_NAME = "testuser@gmail.com";
	
	@Test
	public void findAllRoutesByUser() {
		
		Route route1 = new Route(LocalDate.now());
		Route route2 = new Route(LocalDate.now());
		Route route3 = new Route(LocalDate.now());
		
		User user = new User();
		user.setEmail(USER_NAME);
		user.setPassword("password");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);
		
		routeService.save(route1);
		routeService.save(route2);
		routeService.save(route3);
		
		List<Route> routesBefore = routeService.findAllByUser(user);
		
		assertThat(routesBefore.size()).isEqualTo(0);
		
		user.addRoute(route1);
		user.addRoute(route2);
		user.addRoute(route3);
		
		userService.save(user);
		
		List<Route> routesAfter = routeService.findAllByUser(user);
		
		assertThat(routesAfter.size()).isEqualTo(3);
	}
	
	
	

}
