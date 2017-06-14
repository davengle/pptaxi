package com.example.domain.entity;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;

import com.example.listener.PropaneTaxiRevisionListener;


@Entity
@org.hibernate.envers.RevisionEntity(PropaneTaxiRevisionListener.class)
public class RevisionInfo extends DefaultRevisionEntity{

	private String modifiedBy;

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	


}
