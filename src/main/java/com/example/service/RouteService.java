package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;


public interface RouteService{
	
	
	List<Route> findAll();
	
	List<Route> findAllByRouteDate(LocalDate date);
	
	List<Route>	findAllByRouteDateAndUser(LocalDate date, User user);
	
	List<Route>	findAllByUser(User user);
	
	void save(Route route);

	List<Route> findDistinctStartTimesByRouteDateAndUser(LocalDate date, User user);



}
