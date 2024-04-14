package com.example.demo.patron;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatronRepository extends JpaRepository<Patron, Long> {

    @Query("SELECT p FROM Patron p WHERE p.deletedAt IS NULL")
    List<Patron> findAllNotDeleted();
}
