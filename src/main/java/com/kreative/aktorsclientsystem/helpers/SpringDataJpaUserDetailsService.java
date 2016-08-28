/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.helpers;

import com.kreative.aktorsclientsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Ahmed
 */
@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SpringDataJpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        String[] roles;
        com.kreative.aktorsclientsystem.models.User user = this.userRepository.findByUsername(name);
        if (user.isAdmin()) {
            roles = new String[]{"ROLE_ADMIN"};
        } else {
            roles = new String[]{};
        }
        return new User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(roles));

    }

}
