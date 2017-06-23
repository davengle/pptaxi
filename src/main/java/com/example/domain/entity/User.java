package com.example.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.enums.UserRole;
import com.example.enums.UserStatus;

@Entity
@Audited
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable = false)
	@NotEmpty
	private String email;
	
	private String firstName;
	private String lastName;
	
	
	@Column (nullable = false)
	@NotEmpty
	private String password;
	
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNumber;
	
	
	// @Enumerated(EnumType.STRING)
	private UserStatus status; 
	
	private boolean isActive;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
	private List<Route> routes = new ArrayList<>();

	public User() {
	}
	
	public User(String email, String pword){
		this.email = email;
		this.password = pword;
	}
	
	public User(String email, String pword, String fName) {
		this.email = email;
		this.password = pword;
		this.firstName = fName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getStatus() {
		return isActive;
	}

	public void setStatus(boolean status) {
		isActive = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}


	public List<Route> getRoutes() {
		return routes;
	}

	public void setrRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	public void addRoute(Route route) {
		routes.add(route);
		route.setUser(this);
	}
	
	public void removeRoute(Route route) {
		route.setUser(null);
		this.routes.remove(route);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	
}
