package com.maids.libraryTask.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecordDTO {
    private Long id;
    private Long bookId;
    private Long patronId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

}
