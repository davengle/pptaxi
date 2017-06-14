package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{


	public User findByEmail(String email);
	
	
	
}
