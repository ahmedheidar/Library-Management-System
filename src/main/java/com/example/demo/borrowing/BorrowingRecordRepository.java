package com.example.demo.borrowing;

import com.example.demo.book.Book;
import com.example.demo.patron.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Query("SELECT br FROM BorrowingRecord br WHERE br.book = :book AND br.patron = :patron")
    BorrowingRecord findBorrowingRecordByBookAndPatron(Book book, Patron patron);
}
