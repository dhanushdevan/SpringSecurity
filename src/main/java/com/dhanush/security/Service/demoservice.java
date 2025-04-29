package com.dhanush.security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhanush.security.repository.Company_Repository;
import com.dhanush.security.Entity.Company;
import com.dhanush.security.dto.*;
@Service
public class demoservice {
	@Autowired
	private Company_Repository company;
	@Autowired
	private PasswordEncoder pass;

	public String ok(Student student) {
		Company co= new Company();
		co.setUserName(student.getName());
		co.setPassword(pass.encode(student.getPhone()));
		company.save(co);
		return null;
		
	}

}
