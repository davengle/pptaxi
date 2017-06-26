package com.example.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{


	User findByEmail(String email);
	
	List<Route> findAllRoutesByEmail(String email);
	
	List<User> findAll();
	
}
