package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner bookCommandLineRunner(BookRepository repository) {
        return args -> {
            Book book1 = Book.builder().title("The Great Gatsby")
                    .author("F. Scott Fitzgerald")
                    .isbn("123456789")
                    .pages(218)
                    .createdAt(LocalDate.now())
                    .build();

            Book book2 = Book.builder().title("Clean Code")
                    .pages(464)
                    .publishedYear(1990)
                    .isbn("123456789")
                    .author("Robert C. Martin")
                    .createdAt(LocalDate.now())
                    .build();

            repository.saveAll(
                    List.of(book1, book2)
            );
        };
    }
}
