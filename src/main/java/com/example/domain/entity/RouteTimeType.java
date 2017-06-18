package com.example.domain.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RouteTimeType {
	
	@Id
	@GeneratedValue
	public Long Id;
	
	public RouteTimeType() {}
	
	
	public RouteTimeType(LocalTime localTime) {
		this.startTime = localTime;
	}
	
	public LocalTime startTime;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

}
