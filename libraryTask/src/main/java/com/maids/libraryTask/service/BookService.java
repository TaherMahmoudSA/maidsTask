package com.maids.libraryTask.service;

import com.maids.libraryTask.annotitions.Log;
import com.maids.libraryTask.dto.BookDTO;

import com.maids.libraryTask.entity.Book;

import com.maids.libraryTask.exceptions.ResourceNotFoundException;
import com.maids.libraryTask.mapper.BookMapper;

import com.maids.libraryTask.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;


    private final BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Log
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }
    @Log

    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDto(bookRepository.save(book));
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookDTO updateBook(Long id, BookDTO bookDto) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookMapper.updateBookFromDto(bookDto, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook);
    }
    @Log
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}





