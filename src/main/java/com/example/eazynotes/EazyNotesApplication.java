package com.example.eazynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EazyNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazyNotesApplication.class, args);
    }

}
