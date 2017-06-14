package com.example.service;

import com.example.domain.entity.User;


public interface UserService {

	public User findByEmail(String email);
	
	
	public User save(User user);

}
