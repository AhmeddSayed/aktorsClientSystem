/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.controllers;

import com.kreative.aktorsclientsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kreative.aktorsclientsystem.models.Product;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ahmed
 */
@RestController
@RequestMapping("/products")
@ComponentScan(basePackages = {"com.kreative.aktorsclientsystem.models"})
public class ProductsRestController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Product>> getAllProducts() {
        return new ResponseEntity<>((Collection<Product>) productRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Product> getProductWithId(@PathVariable Long id) {
        return new ResponseEntity<>(productRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<List<Product>> findProductWithName(@RequestParam(value = "name") String productName) {
        return new ResponseEntity<>(productRepository.findByName(productName), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product input) {
        return new ResponseEntity<>(productRepository.save(input), HttpStatus.CREATED);
    }
}
