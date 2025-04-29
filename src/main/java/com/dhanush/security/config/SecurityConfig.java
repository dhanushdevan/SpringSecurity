package com.dhanush.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dhanush.security.Service.DatabseUser;
import com.dhanush.security.Service.JwtFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtFilter filter;

	@Autowired
	public SecurityConfig(JwtFilter filter) {
	    this.filter = filter;
	}

	@Bean
	public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->auth
		.requestMatchers("/auth/login","/register").permitAll().anyRequest().authenticated()
		).csrf(csrf ->csrf.disable())
				.sessionManagement(sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						
						)
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	
		return http.build();	
	}
	
	@Bean
	public UserDetailsService userDeatilsserverice() {
		
//		UserDetails user=User.withUsername("Dhanush")
//				.password(passworden.encode("Deva8838."))
//				.roles("Admin")
//				.build();
//		return new InMemoryUserDetailsManager(user);
		return new DatabseUser();
	}
	@Bean
	public DaoAuthenticationProvider  authprovider() {
	DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
	auth.setUserDetailsService(userDeatilsserverice());
	auth.setPasswordEncoder(passwordEncode());
	return auth;
	}
	@Bean
	public PasswordEncoder passwordEncode() {
		
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}

}
