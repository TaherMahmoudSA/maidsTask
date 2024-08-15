package com.maids.libraryTask.basis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
@SuperBuilder
public class BaseResponse<T> {

    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timestamp;
    private String exception;
    private Object validationErrors;
    private T body;

}
