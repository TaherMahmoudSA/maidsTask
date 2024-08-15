package com.maids.libraryTask.controller;


import com.maids.libraryTask.dto.BorrowingRecordDTO;
import com.maids.libraryTask.service.BookService;
import com.maids.libraryTask.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor

public class BorrowingRecordController {

    private final BorrowingService borrowingRecordService;



    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> createBorrowingRecord(@PathVariable Long bookId, @PathVariable Long patronId,
                                                                    @RequestParam LocalDateTime returnDate) {
        BorrowingRecordDTO savedRecord = borrowingRecordService.borrowBook(bookId,patronId,returnDate);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> returnBook(
            @PathVariable Long bookId,
            @PathVariable Long patronId) {
        BorrowingRecordDTO updatedRecord = borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(updatedRecord);
    }




}

