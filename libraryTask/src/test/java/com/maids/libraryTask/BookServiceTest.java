package com.maids.libraryTask;

import com.maids.libraryTask.dto.BookDTO;
import com.maids.libraryTask.entity.Book;
import com.maids.libraryTask.mapper.BookMapper;
import com.maids.libraryTask.repository.BookRepository;
import com.maids.libraryTask.service.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RequiredArgsConstructor
public class  BookServiceTest {


    @Autowired

    private  BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookMapper bookMapper;

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book(1L, "Book Title", "Author", "ISBN", 2023));
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        Mockito.when(bookMapper.toDto(Mockito.any(Book.class))).thenReturn(new BookDTO(1L, "Book Title", "Author", "ISBN", 2023));

        List<BookDTO> result = bookService.getAllBooks();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Book Title", result.get(0).getTitle());
    }
    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Book Title", "Author", "ISBN", 2023);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Mockito.when(bookMapper.toDto(book)).thenReturn(new BookDTO(bookId, "Book Title", "Author", "ISBN", 2023));

        BookDTO result = bookService.getBookById(bookId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Book Title", result.getTitle());
    }

    @Test
    public void testSaveBook() {
        BookDTO bookDTO = new BookDTO( null,"New Book", "Author", "ISBN", 2024);
        Book book = new Book(null, "New Book", "Author", "ISBN", 2024);
        Mockito.when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Mockito.when(bookMapper.toDto(book)).thenReturn(new BookDTO(1L, "New Book", "Author", "ISBN", 2024));

        BookDTO result = bookService.saveBook(bookDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("New Book", result.getTitle());
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;
        bookService.deleteBook(bookId);
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
    }
}

