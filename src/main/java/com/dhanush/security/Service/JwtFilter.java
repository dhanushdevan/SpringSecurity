package com.dhanush.security.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class JwtFilter extends OncePerRequestFilter {
	
	private final javaJWTUtil jwt;
	private final DatabseUser service;

	@Autowired
	public JwtFilter(javaJWTUtil jwt, DatabseUser service) {
	    this.jwt = jwt;
	    this.service = service;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("its come");
		String token = request.getHeader("Authorization");
		if(token== null || !token.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			return ;
		}
		String realToken=token.substring(7);
		System.out.println(realToken);
		
		try {
			if(jwt.tokenValidate(realToken)) {
			String userName=jwt.etractdata(realToken);
			
				UserDetails user= service.loadUserByUsername(userName);
					UsernamePasswordAuthenticationToken authentication=new 
							UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
				
				}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		filterChain.doFilter(request, response);
		
		
	}

}
