package com.example.domain.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.service.UserService;

@Entity
@Audited
public class Route {
	
	
	@Transient
	@Autowired
	UserService userService;

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate routeDate;
	
	@Column
	@DateTimeFormat(pattern ="HH:mm")
	private LocalTime startTime;	
	
	@Column
	@DateTimeFormat(pattern ="HH:mm")
	private LocalTime endTime;
	
	@OneToOne
	private User user;
	
	
	@Transient
	private Integer quantity;
	
	public  static final Duration ROUTE_DURATION = Duration.ofHours(3);
	
	public Route() {}
	
	public Route(LocalDate routeDate){
		this.setRouteDate(routeDate);
	}

	public LocalDate getRouteDate() {
		return routeDate;
	}

	public void setRouteDate(LocalDate routeDate) {
		this.routeDate = routeDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
		this.endTime = this.startTime.plus(ROUTE_DURATION);
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	
}
