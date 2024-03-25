package com.abclabs.client.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource myDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//############### TEST CODE (Before jdbc authentication) ##################
//		UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication()
//		.withUser(userBuilder.username("kushil").password("test123").roles("EMPLOYEE", "ADMIN"))
//		.withUser(userBuilder.username("rukshan").password("test123").roles("EMPLOYEE", "MANAGER"))
//		.withUser(userBuilder.username("perera").password("test123").roles("PATIENT"));
		//#############################################
		
		auth.jdbcAuthentication().dataSource(myDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		//public access ##################
		.antMatchers("/").permitAll()
		.antMatchers("/register/**").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/contact/**").permitAll()
		//#######################################
		
		//restrict access based on user role(Authority) ##################
		.antMatchers("/auth/admin/**").hasRole("ADMIN")
		.antMatchers("/auth/manager/**").hasRole("MANAGER")
		.antMatchers("/auth/patient/**").hasRole("PATIENT")
		//#######################################
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login/page")
			.loginProcessingUrl("/login/authenticate")
			.permitAll()
		.and()
		.logout()
			.permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/auth/403");
	}
	
}
