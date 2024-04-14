package com.example.demo.patron;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PatronConfig {

    @Bean
    CommandLineRunner patronCommandLineRunner(PatronRepository repository) {
        return args -> {
            Patron patron1 = Patron.builder().name("John Doe")
                    .email("JohnDoe@example.com")
                    .dob(LocalDate.EPOCH)
                    .createdAt(LocalDate.now())
                    .build();

            Patron patron2 = Patron.builder().name("Jane Doe")
                    .email("JaneDoe@example.com")
                    .dob(LocalDate.EPOCH)
                    .createdAt(LocalDate.now())
                    .build();

            repository.saveAll(
                    List.of(patron1, patron2)
            );
        };
    }
}
