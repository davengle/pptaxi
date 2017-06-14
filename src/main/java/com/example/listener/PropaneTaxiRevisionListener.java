package com.example.listener;

import org.hibernate.envers.RevisionListener;

import com.example.domain.entity.RevisionInfo;

public class PropaneTaxiRevisionListener implements RevisionListener{
	
	public String modifiedBy = "TEST_REV_USER";

	@Override
	public void newRevision(Object revisionEntity) {
		
		RevisionInfo localRevisionEntity = (RevisionInfo) revisionEntity;
		localRevisionEntity.setModifiedBy(modifiedBy);
	}

}


