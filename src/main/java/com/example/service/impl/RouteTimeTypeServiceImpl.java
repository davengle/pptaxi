package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.RouteTimeType;
import com.example.repositories.RouteTimeTypeRepository;
import com.example.service.RouteTimeTypeService;


@Service
public class RouteTimeTypeServiceImpl implements RouteTimeTypeService{

	@Autowired 
	RouteTimeTypeRepository routeTimeRepository;
	
	@Override
	public Iterable<RouteTimeType> findAll() {
		return routeTimeRepository.findAll();
	}

}
