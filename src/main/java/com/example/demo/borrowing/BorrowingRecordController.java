package com.example.demo.borrowing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok("Book returned successfully");
    }
    @GetMapping("/borrowing-records")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingRecords() {
        return ResponseEntity.ok(borrowingRecordService.getBorrowingRecords());
    }
}
