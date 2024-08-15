package com.maids.libraryTask;

import com.maids.libraryTask.dto.BorrowingRecordDTO;
import com.maids.libraryTask.entity.Book;
import com.maids.libraryTask.entity.BorrowingRecord;
import com.maids.libraryTask.entity.Patron;
import com.maids.libraryTask.mapper.BorrowingRecordMapper;
import com.maids.libraryTask.repository.BookRepository;
import com.maids.libraryTask.repository.BorrowingRecordRepository;
import com.maids.libraryTask.repository.PatronRepository;
import com.maids.libraryTask.service.BorrowingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class BorrowingServiceTest {

    @Autowired
    private BorrowingService borrowingService;

    @MockBean
    private BorrowingRecordRepository borrowingRecordRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private PatronRepository patronRepository;

    @MockBean
    private BorrowingRecordMapper borrowingRecordMapper;

    @Test

    public void testBorrowBook() {
        Long bookId = 1L;
        Long patronId = 2L;
        Book book = new Book(bookId, "Book Title", "Author", "ISBN", 2023);
        Patron patron = new Patron
                (patronId, "Patron Name", "Contact Info");
        BorrowingRecord record = new BorrowingRecord(null, book, patron, LocalDateTime.now(), null);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Mockito.when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));
        Mockito.when(borrowingRecordRepository.save(Mockito.any(BorrowingRecord.class))).thenReturn(record);
        Mockito.when(borrowingRecordMapper.toDto(record)).thenReturn(new BorrowingRecordDTO(1L, bookId, patronId, LocalDateTime.now(), null));

        BorrowingRecordDTO result = borrowingService.borrowBook(bookId, patronId,LocalDateTime.now().plusDays(5));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bookId, result.getBookId());
        Assertions.assertEquals(patronId, result.getPatronId());
    }

    @Test
    public void testReturnBook() {
        Long bookId = 1L;
        Long patronId = 2L;
        BorrowingRecord record = new BorrowingRecord(1L, new Book(bookId, "Book Title", "Author", "ISBN", 2023), new Patron(patronId, "Patron Name", "Contact Info"), LocalDateTime.now().minusDays(2), null);

        Mockito.when(borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)).thenReturn(Optional.of(record));
        Mockito.when(borrowingRecordRepository.save(record)).thenReturn(record);
        Mockito.when(borrowingRecordMapper.toDto(record)).thenReturn(new BorrowingRecordDTO(1L, bookId, patronId, record.getBorrowDate(), LocalDateTime.now()));

        BorrowingRecordDTO result = borrowingService.returnBook(bookId, patronId);

        Assertions.assertNotNull(result.getReturnDate());
        Assertions.assertEquals(bookId, result.getBookId());
        Assertions.assertEquals(patronId, result.getPatronId());
    }
}

