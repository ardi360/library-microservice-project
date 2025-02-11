package com.project.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    // todo : move all properties file to config server
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
