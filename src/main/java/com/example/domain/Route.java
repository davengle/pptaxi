package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Route {

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Date startTime;	
	
	@Column
	private Date endTime;
	
	
	
	private Route(){
		
	};
	
	public Route(Date startTime, Date endTime){
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Route [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
