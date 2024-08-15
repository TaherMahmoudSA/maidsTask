package com.maids.libraryTask.mapper;

import com.maids.libraryTask.dto.BookDTO;
import com.maids.libraryTask.entity.Book;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);
    void updateBookFromDto(BookDTO bookDto, @MappingTarget Book book);
}

