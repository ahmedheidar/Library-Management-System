package com.example.demo.borrowing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BorrowingRecordConfig {

//    @Bean
//    CommandLineRunner borrowingRecordCommandLineRunner(BorrowingRecordRepository repository) {
//        return args -> {
//            BorrowingRecord borrowingRecord1 = BorrowingRecord.builder()
//                    .book()
//                    .patron(1)
//                    .borrowDate(null)
//                    .build();
//
//            BorrowingRecord borrowingRecord2 = BorrowingRecord.builder()
//                    .book(null)
//                    .patron(null)
//                    .borrowDate(null)
//                    .build();
//
//            repository.saveAll(
//                    List.of(borrowingRecord1, borrowingRecord2)
//            );
//        };
//    }

}
