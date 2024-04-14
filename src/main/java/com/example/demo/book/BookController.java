package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooksNotDeleted();
        return ResponseEntity.ok(books);
    }

    @GetMapping(path = "{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId) {
        Book book = bookService.getBook(bookId);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book addedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
        UpdateResult result = bookService.deleteBook(bookId);

        return switch (result) {
            case SUCCESS -> ResponseEntity.ok().build();
            case BOOK_NOT_FOUND -> ResponseEntity.notFound().build();
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }

    @PutMapping(path = "{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        UpdateResult result = bookService.updateBook(bookId, book);

        return switch (result) {
            case SUCCESS -> ResponseEntity.ok("Book updated successfully");
            case BOOK_NOT_FOUND -> ResponseEntity.notFound().build();
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        };
    }
}
