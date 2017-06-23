package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Route;
import com.example.domain.entity.User;
import com.example.repositories.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if( user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return new UserDetailsImpl(user);
		}
	}
	
	public List<Route> findAllRoutesByEmail(String email){
		return userRepository.findAllRoutesByEmail(email);
	}
	
	public User findOne(User user) {
		return userRepository.findOne(user.getId());
	}
	


	
	

}
