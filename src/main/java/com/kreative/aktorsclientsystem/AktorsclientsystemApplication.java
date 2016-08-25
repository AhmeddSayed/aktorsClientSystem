package com.kreative.aktorsclientsystem;

import com.kreative.aktorsclientsystem.helpers.DBPopulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class AktorsclientsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AktorsclientsystemApplication.class, args);
    }

}
