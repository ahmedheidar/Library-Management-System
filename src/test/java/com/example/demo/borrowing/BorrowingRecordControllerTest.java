package com.example.demo.borrowing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(BorrowingRecordController.class)
@AutoConfigureMockMvc
class BorrowingRecordControllerTest {

    @Autowired
    private BorrowingRecordController borrowingRecordController;

    @MockBean
    private BorrowingRecordService borrowingRecordService;

    @Test
    void borrowBook() {
        Long bookId = 1L;
        Long patronId = 1L;

        doNothing().when(borrowingRecordService).borrowBook(bookId, patronId);

        ResponseEntity<String> responseEntity = borrowingRecordController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book borrowed successfully", responseEntity.getBody());
    }

    @Test
    void returnBook() {
        Long bookId = 1L;
        Long patronId = 1L;

        doNothing().when(borrowingRecordService).returnBook(bookId, patronId);

        ResponseEntity<String> responseEntity = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book returned successfully", responseEntity.getBody());
    }

    @Test
    void getBorrowingRecords() {
        when(borrowingRecordService.getBorrowingRecords()).thenReturn(null);

        ResponseEntity responseEntity = borrowingRecordController.getBorrowingRecords();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}