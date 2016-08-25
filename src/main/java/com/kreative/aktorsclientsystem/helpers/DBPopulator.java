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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ahmed
 */
@Component
public class DBPopulator implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    private final Faker faker = new Faker();
    private final Random randomize = new Random();

    @Bean
    public CommandLineRunner populate() {

        return (args) -> {
            clientRepository.deleteAll();
            productRepository.deleteAll();

            for (int i = 0; i < 20; i++) {
                Client aClient = new Client(faker.name().firstName(), faker.name().lastName());
                aClient.setAddress(faker.address().streetAddress(false));
                aClient.setCountry(faker.country().name());
                aClient.setPhone(faker.phoneNumber().phoneNumber());
                clientRepository.save(aClient);

                Product aProduct = new Product(faker.lorem().sentence(4), randomize.nextInt(100) + 50, faker.lorem().sentence(10), faker.date().past(30, TimeUnit.DAYS));
                productRepository.save(aProduct);
            }

        };

    }

    @Override
    public void run(String... strings) throws Exception {
        populate();
    }

}
