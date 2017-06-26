package com.example.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;

public interface RouteRepository extends CrudRepository<Route, Long> {
	
	
	List<Route> findAll();
	
	List<Route> findAllByRouteDate(LocalDate localDate);
	
	List<Route> findAllByRouteDateAndUser(LocalDate date, User user);
	
	List<Route> findAllByUser(User user);

	Route findFirstByRouteDateAndUser(LocalDate date, User user);

	Route findFirstByRouteDateAndUserAndStartTime(LocalDate routeDate, User unassignedUser, LocalTime startTime);
	

}
