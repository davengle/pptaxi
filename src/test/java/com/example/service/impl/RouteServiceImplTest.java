package com.example.service.impl;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.service.RouteService;
import com.example.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private UserService userService;
	
	User user = new User("email", "password", "unassigned");
	
	
	
	
	@Test
	public void findAllByDateShouldReturnAllExistingRoutesForAGivenDate() {
		LocalDate routeStartDate = LocalDate.of(2017, 01, 01);
		Route route1 = new Route(routeStartDate);
		Route route2 = new Route(routeStartDate);
		
		routeService.save(route1);
		routeService.save(route2);
		List<Route> routes = routeService.findAllByRouteDate(routeStartDate);
		
		assertThat(routes.size()).isEqualTo(2);
	}
	
	@Test
	public void findAllByUser() {
		userService.save(user);
		User returnedUser = userService.findByEmail(user.getEmail());
		
		LocalDate routeStartDate = LocalDate.of(2017, 01, 02);
		Route route1 = new Route(routeStartDate);
		Route route2 = new Route(routeStartDate);
		route1.setUser(returnedUser);
		route2.setUser(returnedUser);
		routeService.save(route1);
		routeService.save(route2);
		
		List<Route> assignedRoutes = routeService.findAllByUser(returnedUser);
		assertThat(assignedRoutes.size()).isEqualTo(2);
	}
	
	@Test
	public void findAllByRouteDateAndByUser() {
		User returnedUser = userService.findByEmail(user.getEmail());
		
		LocalDate routeStartDate1 = LocalDate.of(2017, 01, 03);
		LocalDate routeStartDate2 = LocalDate.of(2017, 01, 04);
		Route route1 = new Route(routeStartDate1);
		Route route2 = new Route(routeStartDate2);
		route1.setUser(returnedUser);
		routeService.save(route1);
		routeService.save(route2);
		
		List<Route> routes = routeService.findAllByRouteDateAndUser(routeStartDate1, returnedUser);
		
		assertThat(routes.size()).isEqualTo(1);
		
	}
	
	@Test
	public void findAllDistinctStartTimesByRouteDateAndUser() {
		User returnedUser = userService.findByEmail("unassigned");
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		
		Route routeCopy1 = new Route(localDate);
		routeCopy1.setStartTime(localTime);
		
		Route routeCopy2 = new Route(localDate);
		routeCopy2.setStartTime(localTime);
		
		Route routeCopy3 = new Route(localDate);
		routeCopy3.setStartTime(localTime);

		routeService.save(routeCopy1);
		routeService.save(routeCopy2);
		routeService.save(routeCopy3);
		
		List<Route> routeStartTimes = routeService.findDistinctStartTimesByRouteDateAndUser(localDate, returnedUser);
		
		assertThat(routeStartTimes.size()).isEqualTo(2);
		
		
		
	}
	
	

}
