package com.dhanush.security.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company_1")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "user_name", unique = true, nullable = false)  // ✅ Fixed column name
    private String userName;  

    @Column(name = "password", nullable = false)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
