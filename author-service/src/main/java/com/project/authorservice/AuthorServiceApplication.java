package com.project.authorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AuthorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorServiceApplication.class, args);
    }

}
