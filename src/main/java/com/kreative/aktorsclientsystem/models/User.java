package com.kreative.aktorsclientsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ahmed
 */
@Entity
public class User implements Serializable {

    // client parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    private Long securityNumber;

    public void setSecurityNumber(Long securityNumber) {
        this.securityNumber = securityNumber;
    }
    private String firstName;
    private String lastName;
    private String username;
    private boolean isAdmin;
    private String phone;
    private String country;
    private String address;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    private @JsonIgnore
    String password;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    // User constructor
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = PASSWORD_ENCODER.encode(password);
        this.isAdmin = isAdmin;
    }

    //for JPA
    public User() {
    }

    public Long getSecurityNumber() {
        return securityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
