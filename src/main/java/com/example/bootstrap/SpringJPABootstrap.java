package com.example.bootstrap;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.domain.entity.Route;
import com.example.domain.entity.RouteTimeType;
import com.example.domain.entity.User;
import com.example.enums.UserRole;
import com.example.repositories.RouteTimeTypeRepository;
import com.example.service.RouteService;
import com.example.service.UserServiceImpl;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RouteService routeService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RouteTimeTypeRepository routeTimeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();

	}

	private void loadData() {
		loadUsers();
		loadRouteTimes();
		loadRoutes();

	}

	private void loadRoutes() {
		Route route1 = new Route(LocalDate.now());
		route1.setStartTime(LocalTime.now());
		routeService.save(route1);

		Route route2 = new Route(LocalDate.now());
		route2.setStartTime(LocalTime.now());
		routeService.save(route2);
		
		Route route3 = new Route(LocalDate.now());
		route3.setStartTime(LocalTime.now());
		routeService.save(route3);
	}

	private void loadRouteTimes() {
		RouteTimeType routeTime = new RouteTimeType(LocalTime.of(0, 0));
		Integer routeTimeIt = 0;
		while (routeTimeIt < 24) {
			routeTimeRepository.save(routeTime);
			routeTime = new RouteTimeType(routeTime.getStartTime().plusHours(1));
			routeTimeIt++;
		}
	}

	private void loadUsers() {
		User user1 = new User("jonny@gmail.com", "j");
		user1.setFirstName("Jonny");
		user1.setLastName("Poe");
		user1.setRole(UserRole.ROLE_ADMIN);
		user1.setStatus(true);
		userService.save(user1);

		User user2 = new User("scott@gmail.com", "s");
		user2.setFirstName("Scott");
		user2.setLastName("Balwinski");
		user2.setRole(UserRole.ROLE_USER);
		user2.setStatus(true);
		userService.save(user2);
	}

}
