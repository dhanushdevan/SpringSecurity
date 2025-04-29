package com.dhanush.security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.security.dto.LoginDto;
import com.dhanush.security.Service.javaJWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	public AuthenticationManager authmanager;
	
	@Autowired
	private javaJWTUtil jwt;
	
	@PostMapping("/login")
	public String control(@RequestBody LoginDto company) {
		
		System.out.println("Controller called");
		Authentication authenticatio=authmanager.authenticate(
				(new UsernamePasswordAuthenticationToken(company.getUserName(), company.getPassword())));
		
		UserDetails user=(UserDetails) authenticatio.getPrincipal();
		
		String jwtToken=jwt.generateToken(user);
		return jwtToken;
		
	}
	
	@GetMapping("/demo")
	public String name() {
		return "ok";
	}

}
