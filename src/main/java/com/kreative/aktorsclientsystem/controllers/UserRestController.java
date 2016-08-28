/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kreative.aktorsclientsystem.models.User;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestParam;
import com.kreative.aktorsclientsystem.repositories.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Ahmed
 */
/*
@Controller
@RequestMapping("/users")
@MessageMapping("/aktors")
@ComponentScan(basePackages = {"com.kreative.aktorsusersystem.models"})
 */
public class UserRestController {
    /*
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @SendTo("/topic/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /*
    @MessageMapping("/aktors")
    @SendTo("/topic/users")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUserWithId(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @MessageMapping("/aktors")
    @SendTo("/topic/users")
    @RequestMapping(method = RequestMethod.GET, params = {"lastName"})
    public List<User> findUserWithName(@RequestParam(value = "userName") String userName) {
        return userRepository.findByLastName(userName);

    }

    @SendTo("/topic/newUser")
    @RequestMapping(method = RequestMethod.POST, params = {"username", "password", "isAdmin"})
    public User addUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "isAdmin") boolean isAdmin) {
        return userRepository.save(new User(username, password, isAdmin));
    }
     */
}
