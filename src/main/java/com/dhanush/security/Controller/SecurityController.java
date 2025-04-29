package com.dhanush.security.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.security.Entity.Company;
import com.dhanush.security.Service.demoservice;
import com.dhanush.security.dto.Student;

import java.util.ArrayList;
import java.util.List;



@RestController
public class SecurityController {

    private List<Student> students = new ArrayList<>();
    
    @Autowired
    private demoservice demo;

    @GetMapping("/home")
    
    public String  defaulst() {
    	return "hello";
    }
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public String getCsrfToken(HttpServletRequest request) {
    	try {
        return (String) request.getAttribute("_csrf");
    	}
    	catch (Exception e) {
			return e.getMessage();
		}
    }
    @PostMapping("/register") 
    public Student addStudent(@RequestBody Student student) {
    	demo.ok(student);
        return student;
    }
    
//    @PostMapping("/login")
//    public String loginuser(@RequestBody Company co) {
////    	String name=pass.hashPassword("Deva8838.");
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = "Deva8838.";
//        String hashedPassword = encoder.encode(rawPassword);
//
//        System.out.println("Hashed password: " + hashedPassword);
//    	return null;
//    }

}