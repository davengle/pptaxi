package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		
		auth
			.inMemoryAuthentication()
				.withUser("jonny@gmail.com")
				.password("password")
				.roles("ADMIN")
			.and()
				.withUser("scott@gmail.com")
				.password("password")
				.roles("DRIVER");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/driver/**").hasAnyRole("DRIVER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/console").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
