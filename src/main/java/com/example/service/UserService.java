package com.example.service;

import java.util.List;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;


public interface UserService {

	User findByEmail(String email);
	
	User save(User user);
	
	List<Route> findAllRoutesByEmail(String email);

	User findOne(Long id);
}
