package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.domain.entity.Route;


public interface RouteService {
	
	List<Route> findAllByRouteDate(LocalDate localDate);
	
	void save(Route route);

}
