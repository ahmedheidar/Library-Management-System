package com.example.demo.borrowing;

import com.example.demo.book.Book;
import com.example.demo.patron.Patron;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord {
    @Id
    @SequenceGenerator(
            name = "borrowing_record_sequence",
            sequenceName = "borrowing_record_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "borrowing_record_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public BorrowingRecord(Book book, Patron patron, LocalDateTime borrowDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
    }
}
