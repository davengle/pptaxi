package com.example.enums;

public enum UserRole {
	
	ROLE_ADMIN,
	ROLE_USER;
	
	public String getRole() {
		return name();
	}

}
