/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ahmed
 */
@RestController
@RequestMapping("/orders")
@ComponentScan(basePackages = {"com.kreative.aktorsclientsystem.models"})
public class OrdersController {
    
}
