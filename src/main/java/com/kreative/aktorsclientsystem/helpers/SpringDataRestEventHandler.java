/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.helpers;

import com.kreative.aktorsclientsystem.models.User;
import com.kreative.aktorsclientsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ahmed
 */
@Component
@RepositoryEventHandler(User.class)
public class SpringDataRestEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public SpringDataRestEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @HandleBeforeCreate
    public void applyUserInformationUsingSecurityContext(User client) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            User newUser = client;
            this.userRepository.save(newUser);
        }
    }
}
