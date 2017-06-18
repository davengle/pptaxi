package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class DataLoaderService {

	
//	@Autowired
//	private RouteService routeService;
//	
//	@Autowired
//	private UserServiceImpl userService;
//	
//	@Autowired
//	private RouteTimeTypeRepository routeTimeRepository;
//	
//	
//	public DataLoaderService() {
//	
//	}
//		
//	
//	
//	@PostConstruct
//	private void loadData(){
//		
//		RouteTimeType routeTime = new RouteTimeType(LocalTime.of(0, 0));
//		Integer routeTimeIt = 0;
//		while (routeTimeIt < 24) {
//			routeTimeRepository.save(routeTime);
//			routeTime = new RouteTimeType(routeTime.getStartTime().plusHours(1));
//			routeTimeIt++;
//		}
//		
//		Route route1 = new Route(LocalDate.now());
//		route1.setStartTime(LocalTime.now());
//		routeService.save(route1);
//
//		Route route2 = new Route(LocalDate.now());
//		route2.setStartTime(LocalTime.now());
//		routeService.save(route2);
//		
//		User user1 = new User("jonny@gmail.com", "p");
//		user1.setFirstName("Jonny");
//		user1.setLastName("Poe");
//		user1.setRole(UserRole.ROLE_ADMIN);
//		user1.setStatus(true);
//		userService.save(user1);
//
//		User user2 = new User("scott@gmail.com", "p");
//		user2.setFirstName("Scott");
//		user2.setLastName("Balwinski");
//		user2.setRole(UserRole.ROLE_USER);
//		user2.setStatus(true);
//		userService.save(user2);
//		
//		
//	}
	
}
