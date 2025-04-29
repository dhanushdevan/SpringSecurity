package com.dhanush.security.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhanush.security.dto.Student;
import com.dhanush.security.Entity.Company;
import com.dhanush.security.config.SecurityConfig;
import com.dhanush.security.dto.*;
import com.dhanush.security.repository.*;
@Service
public class DatabseUser implements UserDetailsService {
	

	
@Autowired
 public Company_Repository  company_Repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Company stu = company_Repository.findByUserName(username);
		String password=stu.getPassword();
		return new User(stu.getUserName(), password,Collections.singleton(new SimpleGrantedAuthority("admin")));
	}
}
