/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.helpers;

import com.github.javafaker.Faker;
import com.kreative.aktorsclientsystem.models.User;
import com.kreative.aktorsclientsystem.models.Product;
import com.kreative.aktorsclientsystem.repositories.ProductRepository;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.kreative.aktorsclientsystem.repositories.UserRepository;

/**
 *
 * @author Ahmed
 */
@Component
public class DBPopulator implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private final Faker faker = new Faker();
    private final Random randomize = new Random();

    @Bean
    public CommandLineRunner populate() {

        return (args) -> {
            // creating an admin
            String firstname, lastname;
            User aUser = new User("admin", "1234", true);
            aUser.setFirstName(faker.name().firstName());
            aUser.setSecurityNumber(Math.abs(randomize.nextLong()));
            aUser.setLastName(faker.name().lastName());
            aUser.setAddress(faker.address().streetAddress(false));
            aUser.setCountry(faker.country().name());
            aUser.setPhone(faker.phoneNumber().phoneNumber());
            userRepository.save(aUser);

            // creating a normal user
            aUser = new User("user", "1234", false);
            aUser.setFirstName(faker.name().firstName());
            aUser.setSecurityNumber(Math.abs(randomize.nextLong()));
            aUser.setLastName(faker.name().lastName());
            aUser.setAddress(faker.address().streetAddress(false));
            aUser.setCountry(faker.country().name());
            aUser.setPhone(faker.phoneNumber().phoneNumber());
            userRepository.save(aUser);

            for (int i = 0; i < 20; i++) {
                // creating normal users
                firstname = faker.name().firstName();
                lastname = faker.name().lastName();
                aUser = new User(firstname+lastname, lastname, false);
                aUser.setFirstName(firstname);
                aUser.setSecurityNumber(Math.abs(randomize.nextLong()));
                aUser.setLastName(lastname);
                aUser.setAddress(faker.address().streetAddress(false));
                aUser.setCountry(faker.country().name());
                aUser.setPhone(faker.phoneNumber().phoneNumber());
                userRepository.save(aUser);

                // creating products
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
