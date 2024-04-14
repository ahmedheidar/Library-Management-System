package com.example.demo.patron;

import com.example.demo.book.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }
    public List<Patron> getPatrons() {
        return patronRepository.findAllNotDeleted();
    }

    public Patron getPatron(Long patronId) {
        return patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalStateException("Patron with id " + patronId + " does not exist"));
    }

    public Patron addPatron(Patron patron) {
        patron.setCreatedAt(LocalDate.now());
        return patronRepository.save(patron);
    }
    @Transactional
    public void updatePatron(Long patronId, Patron patron) {
        Patron patronToUpdate = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalStateException("Patron with id " + patronId + " does not exist"));

        if (patron.getName() != null) {
            patronToUpdate.setName(patron.getName());
        }

        if (patron.getEmail() != null) {
            patronToUpdate.setEmail(patron.getEmail());
        }

        if (patron.getPhone() != null) {
            patronToUpdate.setPhone(patron.getPhone());
        }

        if (patron.getAddress() != null) {
            patronToUpdate.setAddress(patron.getAddress());
        }

        if (patron.getCity() != null) {
            patronToUpdate.setCity(patron.getCity());
        }

        if (patron.getCountry() != null) {
            patronToUpdate.setCountry(patron.getCountry());
        }

        patronToUpdate.setUpdatedAt(LocalDate.now());
    }

    public void deletePatron(Long patronId) {
        boolean exists = patronRepository.existsById(patronId);
        if (!exists) {
            throw new IllegalStateException("Patron with id " + patronId + " does not exist");
        }
        patronRepository.findById(patronId)
                .ifPresent(patron -> {
                    patron.setDeletedAt(LocalDate.now());
                    patronRepository.save(patron);
                });
    }
}