package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.service.impl.UserServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	 @Autowired
	 private UserServiceImpl userService;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		
		auth
			.userDetailsService(userService);
	}
	
	/*
	 * Role Hierarchy is used to enable authentication fall through.
	 * i.e. An Admin can do everything a user can do and a user can do everything a guest can do.
	 * roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER > ROLE_GUEST")
	 */
	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/driver/**").hasAnyRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/console/**").permitAll()
				.anyRequest().authenticated().and()
				.formLogin()
					.loginPage("/login")
					.permitAll().and()
				.logout()
					.logoutSuccessUrl("/login?logout").permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
