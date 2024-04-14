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

    public UpdateResult deleteBook(Long bookId) {
        Book bookToDelete = bookRepository.findById(bookId)
                .orElse(null);

        if (bookToDelete == null) {
            return UpdateResult.BOOK_NOT_FOUND;
        }

        bookToDelete.setDeletedAt(LocalDate.now());

        return UpdateResult.SUCCESS;
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));
    }

    @Transactional
    public UpdateResult updateBook(Long bookId, Book book) {
        Book bookToUpdate = bookRepository.findById(bookId)
                .orElse(null);

        if (bookToUpdate == null) {
            return UpdateResult.BOOK_NOT_FOUND;
        }

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

        return UpdateResult.SUCCESS;
    }
}

