package com.example.demo.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;


    @Test
    void getBooks() {
        List<Book> mockBooks = List.of(
                Book.builder()
                        .id(1L)
                        .title("Book 1")
                        .author("Author 1")
                        .build(),
                Book.builder()
                        .id(2L)
                        .title("Book 2")
                        .author("Author 2")
                        .build()
        );

        when(bookService.getBooksNotDeleted()).thenReturn(mockBooks);

        ResponseEntity<List<Book>> responseEntity = bookController.getBooks();

        List<Book> books = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBooks, books);
    }

    @Test
    void getBook() {
        Book mockBook = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .build();

        when(bookService.getBook(1L)).thenReturn(mockBook);

        ResponseEntity<Book> responseEntity = bookController.getBook(1L);

        Book book = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBook, book);
    }

    @Test
    void addBook() {
        Book mockBook = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .build();

        when(bookService.addBook(mockBook)).thenReturn(mockBook);

        ResponseEntity<Book> responseEntity = bookController.addBook(mockBook);

        Book book = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockBook, book);
    }

    @Test
    void deleteBook() {
        when(bookService.deleteBook(1L)).thenReturn(UpdateResult.SUCCESS);

        ResponseEntity<Void> responseEntity = bookController.deleteBook(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateBook() {
        Book mockBook = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .build();

        when(bookService.updateBook(1L, mockBook)).thenReturn(UpdateResult.SUCCESS);

        ResponseEntity<String> responseEntity = bookController.updateBook(1L, mockBook);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book updated successfully", responseEntity.getBody());
    }
}