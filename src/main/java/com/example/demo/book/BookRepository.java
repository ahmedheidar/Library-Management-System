package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.deletedAt IS NULL")
    List<Book> findAllBooksNotDeleted();

    @Query("SELECT b FROM Book b WHERE b.deletedAt IS NULL AND b.id = :id")
    Book findBookNotDeletedById(Long id);
}
