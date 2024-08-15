package com.maids.libraryTask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
@NotNull
    private String title;
    @NotNull

    private String author;
    @NotNull

    private String isbn;
    @NotNull

    private Integer publicationYear;

}

