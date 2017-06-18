package com.example.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.entity.Route;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {
	
	@Autowired
	private RouteService routeService;
	
	@Test
	public void findAllByDateShouldReturnAllExistingRoutesForAGivenDate() {
		
		List<Route> routes = routeService.findAllByRouteDate(LocalDate.now());
		assertThat(routes.size()).isEqualTo(3);
	}

}
