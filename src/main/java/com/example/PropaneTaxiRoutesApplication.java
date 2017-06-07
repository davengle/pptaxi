package com.example;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Route;
import com.example.repositories.RouteRepository;
import com.example.service.DataLoaderService;


@SpringBootApplication
public class PropaneTaxiRoutesApplication {
	
	
	Logger logger = LoggerFactory.getLogger(PropaneTaxiRoutesApplication.class);
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired 
	DataLoaderService dataLoader;
	
	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(PropaneTaxiRoutesApplication.class, args);
	}
	
	@PostConstruct
	void seeRoutes(){
		
		logger.info("see Routes method called");
		
		Iterable<Route> routes = routeRepository.findAll();
		
		
		for(Route route: routeRepository.findAll()){
			logger.info(route.toString());
			route.setStartTime(new Date());
			routeRepository.save(route);
		}
		
		
	}
}
