package com.example.demo.borrowing;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.patron.Patron;
import com.example.demo.patron.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;


    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository,
                            BookRepository bookRepository,
                            PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findBookNotDeletedById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }

        Patron patron = patronRepository.findNotDeletedById(patronId);
        if (patron == null) {
            throw new IllegalArgumentException("Patron not found with id: " + patronId);
        }

        LocalDateTime borrowDate = LocalDateTime.now();
        BorrowingRecord borrowingRecord = new BorrowingRecord(book, patron, borrowDate);

        borrowingRecordRepository.save(borrowingRecord);
    }


    public void returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findBookNotDeletedById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }

        Patron patron = patronRepository.findNotDeletedById(patronId);
        if (patron == null) {
            throw new IllegalArgumentException("Patron not found with id: " + patronId);
        }

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findBorrowingRecordByBookAndPatron(book, patron);
        if (borrowingRecord == null) {
            throw new IllegalArgumentException("Borrowing record not found for book with id: " + bookId + " and patron with id: " + patronId);
        }

        borrowingRecord.setReturnDate(LocalDateTime.now());
        borrowingRecordRepository.save(borrowingRecord);
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }
}
