package com.example.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.entity.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {
	
	
	public List<Route> findAllByRouteDate(LocalDate localDate);

}
