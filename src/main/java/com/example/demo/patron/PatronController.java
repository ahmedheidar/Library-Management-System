package com.example.demo.patron;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Patron> getPatrons() {
        return patronService.getPatrons();
    }

    @GetMapping(path = "{patronId}")
    public Patron getPatron(@PathVariable("patronId") Long patronId) {
        return patronService.getPatron(patronId);
    }

    @PostMapping
    public Patron addPatron(@RequestBody Patron patron) {
        return patronService.addPatron(patron);
    }

    @PutMapping(path = "{patronId}")
    public void updatePatron(@PathVariable("patronId") Long patronId, @RequestBody Patron patron) {
        patronService.updatePatron(patronId, patron);
    }

    @DeleteMapping(path = "{patronId}")
    public void deletePatron(@PathVariable("patronId") Long patronId) {
        patronService.deletePatron(patronId);
    }
}
