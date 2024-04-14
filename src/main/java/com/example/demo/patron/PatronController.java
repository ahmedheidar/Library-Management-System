package com.example.demo.patron;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons() {
        List<Patron> patrons = patronService.getPatrons();
        return ResponseEntity.ok(patrons);
    }

    @GetMapping(path = "{patronId}")
    public ResponseEntity<Patron> getPatron(@PathVariable("patronId") Long patronId) {
        Patron patron = patronService.getPatron(patronId);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        Patron addedPatron = patronService.addPatron(patron);
        return ResponseEntity.ok(addedPatron);
    }

    @PutMapping(path = "{patronId}")
    public ResponseEntity<String> updatePatron(@PathVariable("patronId") Long patronId, @RequestBody Patron patron) {
        UpdateResult result = patronService.updatePatron(patronId, patron);

        return switch (result) {
            case SUCCESS -> ResponseEntity.ok("Patron updated successfully");
            case PATRON_NOT_FOUND -> ResponseEntity.notFound().build();
            default -> ResponseEntity.status(500).body("Unexpected error occurred");
        };
    }

    @DeleteMapping(path = "{patronId}")
    public ResponseEntity<Void> deletePatron(@PathVariable("patronId") Long patronId) {
        UpdateResult result = patronService.deletePatron(patronId);

        return switch (result) {
            case SUCCESS -> ResponseEntity.ok().build();
            case PATRON_NOT_FOUND -> ResponseEntity.notFound().build();
            default -> ResponseEntity.status(500).build();
        };
    }
}

