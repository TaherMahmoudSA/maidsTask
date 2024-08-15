package com.maids.libraryTask.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatronDTO {
    private Long id;
    private String name;
    private String contactInformation;

}
