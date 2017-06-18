package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Route;
import com.example.repositories.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService{

	
	@Autowired
	RouteRepository routeRepository;
	
	
	@Override
	public List<Route> findAllByRouteDate(LocalDate localDate) {
		return routeRepository.findAllByRouteDate(localDate);
	}


	@Override
	public void save(Route route) {
		routeRepository.save(route);
	}

}
