package com.kreative.aktorsclientsystem;

import com.github.javafaker.Faker;
import com.kreative.aktorsclientsystem.helpers.DBPopulator;
import com.kreative.aktorsclientsystem.models.Client;
import com.kreative.aktorsclientsystem.models.Product;
import com.kreative.aktorsclientsystem.repositories.ClientRepository;
import com.kreative.aktorsclientsystem.repositories.ProductRepository;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AktorsclientsystemApplication {

    private final Faker faker = new Faker();
    private final Random randomize = new Random();

    public static void main(String[] args) {
        SpringApplication.run(AktorsclientsystemApplication.class, args);

    }

    @Bean
    public CommandLineRunner populateClients(ClientRepository clientRepository) {

        return (args) -> {
            clientRepository.deleteAll();

            for (int i = 0; i < 20; i++) {
                Client aClient = new Client(faker.name().firstName(), faker.name().lastName());
                aClient.setAddress(faker.address().toString());
                aClient.setCountry(faker.country().name());
                aClient.setPhone(faker.phoneNumber().phoneNumber());
                clientRepository.save(aClient);
            }

            System.err.println("=================================================== " + clientRepository.count());

        };

    }

    @Bean
    public CommandLineRunner populateProducts(ProductRepository productRepository) {

        return (args) -> {
            productRepository.deleteAll();

            for (int i = 0; i < 20; i++) {
                Product aProduct = new Product(faker.lorem().sentence(4), randomize.nextInt(100) + 50, faker.lorem().sentence(10), faker.date().past(30, TimeUnit.DAYS));
                productRepository.save(aProduct);
            }

            System.err.println("=================================================== " + productRepository.count());
        };
    }
}
