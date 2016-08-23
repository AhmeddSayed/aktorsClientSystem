package com.kreative.aktorsclientsystem;

import com.github.javafaker.Faker;
import com.kreative.aktorsclientsystem.models.Client;
import com.kreative.aktorsclientsystem.repositories.ClientRepository;
import java.io.Serializable;
import java.util.Random;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@SpringBootApplication
public class AktorsclientsystemApplication {

    private final Faker faker = new Faker();
    private Random randomize = new Random();

    public static void main(String[] args) {
        SpringApplication.run(AktorsclientsystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDb(ClientRepository clientRepository) {
        return (args) -> {
            clientRepository.deleteAll();

            for (int i = 0; i < 20; i++) {
                Client aClient = new Client(faker.name().firstName(), faker.name().lastName());
                clientRepository.save(aClient);
            }
        };
    }
}
