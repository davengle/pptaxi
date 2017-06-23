package com.example.enums;

public enum UserRole {
	
	ROLE_USER,
	ROLE_ADMIN;
	
	public String getRole() {
		return name();
	}

}
