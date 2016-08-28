/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.repositories;

import com.kreative.aktorsclientsystem.models.User;
import java.util.Collection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ahmed
 */
//@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    Collection<User> findByLastName(String lastName);

    @Override
    public Iterable<User> findAll();
    
    
    @Override
    User save(@Param("user") User user);

    @Override
    void delete(@Param("id") Long id);

    @Override
    void delete(@Param("user") User user);

    @Override
    public User findOne(@Param("id") Long id);
}
