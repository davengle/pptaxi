package com.example.bootstrap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
import com.example.service.UserService;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RouteService routeService;

	@Autowired
	private UserService userService;

	@Autowired
	private RouteTimeTypeRepository routeTimeRepository;
	
	
	LocalDate yesterday = LocalDate.now().minusDays(1);
	LocalDate today = LocalDate.now();
	LocalDate tomorrow = LocalDate.now().plusDays(1);
	Route route1 = new Route(yesterday);
	Route route2 = new Route(today);
	Route route3 = new Route(today);
	Route route4 = new Route(tomorrow);
	Route route5 = new Route(tomorrow);
	Route route6 = new Route(tomorrow);
	User jonny = new User("jonny@gmail.com", "j");
	User scott = new User("scott@gmail.com", "s");

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();

	}

	private void loadData() {
		loadUsers();
		loadRouteTimes();
		loadRoutes();
		assignRoutes();

	}

	private void loadRoutes() {
		
		//Yesterday - 1 route
		route1.setStartTime(LocalTime.now().truncatedTo(ChronoUnit.HOURS));
		routeService.save(route1);
		
		
		//Today - 2 routes
		route2.setStartTime(LocalTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS));
		route3.setStartTime(LocalTime.now().plusHours(2).truncatedTo(ChronoUnit.HOURS));
		routeService.save(route2);
		routeService.save(route3);
		
		
		//Tomorrow - 3 routes
		route4.setStartTime(LocalTime.now().truncatedTo(ChronoUnit.HOURS));
		route5.setStartTime(LocalTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS));
		route6.setStartTime(LocalTime.now().plusHours(2).truncatedTo(ChronoUnit.HOURS));
		routeService.save(route4);
		routeService.save(route5);
		routeService.save(route6);
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
		
		jonny.setFirstName("Jonny");
		jonny.setLastName("Poe");
		jonny.setRole(UserRole.ROLE_ADMIN);
		jonny.setStatus(true);
		userService.save(jonny);
		
		scott.setFirstName("Scott");
		scott.setLastName("Balwinski");
		scott.setRole(UserRole.ROLE_USER);
		scott.setStatus(true);
		userService.save(scott);
	}
	
	
	private void assignRoutes() {
		List<Route> routes = routeService.findAll();
		Route routeYesterday = routes.get(0);
		Route routeToday = routes.get(1);
		Route routeTomorrow = routes.get(3);
		routeYesterday.setUser(jonny);	
		routeToday.setUser(jonny);	
		routeTomorrow.setUser(jonny);	
		routeService.save(routeYesterday);
		routeService.save(routeToday);
		routeService.save(routeTomorrow);
	}
	

}
