package com.example;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.entity.Route;
import com.example.repositories.RouteRepository;
import com.example.service.DataLoaderService;


@SpringBootApplication
public class PropaneTaxiRoutesApplication {
	
	
	Logger logger = LoggerFactory.getLogger(PropaneTaxiRoutesApplication.class);
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired 
	DataLoaderService dataLoader;
	

	public static void main(String[] args) {
		SpringApplication.run(PropaneTaxiRoutesApplication.class, args);
	}
	
}
