package com.example.demo.patron;

import com.example.demo.book.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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
    public UpdateResult updatePatron(Long patronId, Patron patron) {
        Patron patronToUpdate = patronRepository.findById(patronId)
                .orElse(null);

        if (patronToUpdate == null) {
            return UpdateResult.PATRON_NOT_FOUND;
        }

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

        return UpdateResult.SUCCESS;
    }

    public UpdateResult deletePatron(Long patronId) {
        Patron patronToDelete = patronRepository.findById(patronId)
                .orElse(null);

        if (patronToDelete == null) {
            return UpdateResult.PATRON_NOT_FOUND;
        }

        patronToDelete.setDeletedAt(LocalDate.now());

        return UpdateResult.SUCCESS;
    }
}
