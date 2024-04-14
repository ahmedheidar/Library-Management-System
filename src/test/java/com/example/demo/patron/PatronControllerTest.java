package com.example.demo.patron;

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

@WebMvcTest(PatronController.class)
@AutoConfigureMockMvc
class PatronControllerTest {

    @Autowired
    private PatronController patronController;

    @MockBean
    private PatronService patronService;

    @Test
    void getPatrons() {
        List<Patron> mockPatrons = List.of(
                Patron.builder()
                        .id(1L)
                        .name("Patron 1")
                        .email("patron1@email.com")
                        .phone("123-456-7890")
                        .address("123 Main St")
                        .city("City 1")
                        .build(),
                Patron.builder()
                        .id(2L)
                        .name("Patron 2")
                        .email("patron2@email.com")
                        .phone("123-456-7890")
                        .address("123 Main St")
                        .city("City 2")
                        .build()
        );

        when(patronService.getPatrons()).thenReturn(mockPatrons);

        ResponseEntity<List<Patron>> responseEntity = patronController.getPatrons();

        List<Patron> patrons = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPatrons, patrons);
    }

    @Test
    void getPatron() {
        Patron mockPatron = Patron.builder()
                .id(1L)
                .name("Patron 1")
                .email("patron1@email.com")
                .phone("123-456-7890")
                .address("123 Main St")
                .city("City 1")
                .build();

        when(patronService.getPatron(1L)).thenReturn(mockPatron);

        ResponseEntity<Patron> responseEntity = patronController.getPatron(1L);

        Patron patron = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPatron, patron);
    }

    @Test
    void addPatron() {
        Patron mockPatron = Patron.builder()
                .id(1L)
                .name("Patron 1")
                .email("patron1@email.com")
                .phone("123-456-7890")
                .address("123 Main St")
                .city("City 1")
                .build();

        when(patronService.addPatron(mockPatron)).thenReturn(mockPatron);

        ResponseEntity<Patron> responseEntity = patronController.addPatron(mockPatron);

        Patron patron = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPatron, patron);
    }

    @Test
    void updatePatron() {
        Patron mockPatron = Patron.builder()
                .id(1L)
                .name("Patron 1")
                .email("patron1@email.com")
                .phone("123-456-7890")
                .address("123 Main St")
                .city("City 1")
                .build();

        when(patronService.updatePatron(1L, mockPatron)).thenReturn(UpdateResult.SUCCESS);

        ResponseEntity<String> responseEntity = patronController.updatePatron(1L, mockPatron);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Patron updated successfully", responseEntity.getBody());
    }

    @Test
    void deletePatron() {
        when(patronService.deletePatron(1L)).thenReturn(UpdateResult.SUCCESS);

        ResponseEntity<Void> responseEntity = patronController.deletePatron(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}