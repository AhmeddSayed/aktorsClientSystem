/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.helpers;

import com.github.javafaker.Faker;
import com.kreative.aktorsclientsystem.models.Client;
import com.kreative.aktorsclientsystem.models.Product;
import com.kreative.aktorsclientsystem.repositories.ClientRepository;
import com.kreative.aktorsclientsystem.repositories.ProductRepository;
import java.sql.Time;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Ahmed
 */
public class DBPopulator {

    private ClientRepository clientRepository;
    private ProductRepository productRepository;

}
