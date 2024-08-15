package com.maids.libraryTask.service;

import com.maids.libraryTask.annotitions.Log;
import com.maids.libraryTask.dto.BorrowingRecordDTO;
import com.maids.libraryTask.entity.Book;
import com.maids.libraryTask.entity.BorrowingRecord;
import com.maids.libraryTask.entity.Patron;
import com.maids.libraryTask.exceptions.ResourceNotFoundException;
import com.maids.libraryTask.mapper.BorrowingRecordMapper;
import com.maids.libraryTask.repository.BookRepository;
import com.maids.libraryTask.repository.BorrowingRecordRepository;
import com.maids.libraryTask.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class BorrowingService {


    private final BorrowingRecordRepository borrowingRecordRepository;

    private final BookRepository bookRepository;

    private final PatronRepository patronRepository;

    private final BorrowingRecordMapper borrowingRecordMapper;

    @Log
    public BorrowingRecordDTO borrowBook(Long bookId,Long patronId,LocalDateTime returnDate) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setReturnDate(returnDate);
        record.setBorrowDate(LocalDateTime.now());

        return borrowingRecordMapper.toDto(borrowingRecordRepository.save(record));
    }

    @Log
    public BorrowingRecordDTO returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found for book " + bookId + " and patron " + patronId));

        record.setReturnDate(LocalDateTime.now());

        return borrowingRecordMapper.toDto(borrowingRecordRepository.save(record));
    }
}
