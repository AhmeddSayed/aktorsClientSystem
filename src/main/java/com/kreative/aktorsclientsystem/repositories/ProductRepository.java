/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.repositories;

import com.kreative.aktorsclientsystem.models.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmed
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

}
