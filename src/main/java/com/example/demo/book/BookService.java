package com.example.demo.book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksNotDeleted() {
        return bookRepository.findAllBooksNotDeleted();
    }

    public Book addBook(Book book) {
        book.setCreatedAt(LocalDate.now());
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("Book with id " + bookId + " does not exist");
        }
        bookRepository.findById(bookId)
                .ifPresent(book -> {
                    book.setDeletedAt(LocalDate.now());
                    bookRepository.save(book);
                });
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));
    }

    @Transactional
    public void updateBook(Long bookId, Book book) {
        Book bookToUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));

        if (book.getTitle() != null) {
            bookToUpdate.setTitle(book.getTitle());
        }

        if (book.getAuthor() != null) {
            bookToUpdate.setAuthor(book.getAuthor());
        }

        if (book.getIsbn() != null) {
            bookToUpdate.setIsbn(book.getIsbn());
        }

        if (book.getDescription() != null) {
            bookToUpdate.setDescription(book.getDescription());
        }

        if (book.getPages() != 0) {
            bookToUpdate.setPages(book.getPages());
        }

        if (book.getPublishedYear() != 0) {
            bookToUpdate.setPublishedYear(book.getPublishedYear());
        }

        bookToUpdate.setUpdatedAt(LocalDate.now());
    }
}
