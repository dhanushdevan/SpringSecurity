package com.dhanush.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dhanush.security.Entity.Company;
@Repository
public interface Company_Repository extends JpaRepository<Company,Integer>{
	
	Company findByUserName(String userName); 
}
