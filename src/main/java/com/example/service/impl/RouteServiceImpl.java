package com.example.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.repositories.RouteRepository;
import com.example.service.RouteService;
import com.example.service.UserService;

@Service
public class RouteServiceImpl implements RouteService{

	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	UserService userService;
	
	
	
	@Override
	public List<Route> findAll(){
		return routeRepository.findAll();
	}
	
	@Override
	public List<Route> findAllByRouteDate(LocalDate localDate) {
		return routeRepository.findAllByRouteDate(localDate);
	}
	
	
	@Override
	public List<Route> findAllByRouteDateAndUser(LocalDate date, User user) {
		return routeRepository.findAllByRouteDateAndUser(date, user);
	}

	
	/*
	 * Method to find routes with distinct route start times based on route date and user.
	* Could not find a way to make repo query return only the unique values that I wanted
	* Return list is sorted by startTime ascending
	*/
	@Override
	public List<Route> findDistinctStartTimesByRouteDateAndUser(LocalDate date, User user){
		List<Route> distinctStartTimes = new ArrayList<>();
		List<Route> initialRoutes = routeRepository.findAllByRouteDateAndUser(date, user);
		Set<LocalTime> startTimes = new HashSet<>();
		
		for (  Route route : initialRoutes) {
			boolean added = startTimes.add(route.getStartTime());
			if (added) {
				distinctStartTimes.add(route);
			}
		}
		
		distinctStartTimes.sort((Route r1, Route r2)->r1.getStartTime().compareTo(r2.getStartTime()));
		return distinctStartTimes;
	}
	
	@Override
	public void save(Route route) {
		if(null==route.getUser()) {
			route.setUser(userService.findByEmail("unassigned"));
		}
		routeRepository.save(route);
	}

	@Override
	public List<Route> findAllByUser(User user) {
		return routeRepository.findAllByUser(user);
	}



}

	


