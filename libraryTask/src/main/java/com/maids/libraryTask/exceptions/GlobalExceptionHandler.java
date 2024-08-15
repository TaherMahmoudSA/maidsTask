package com.maids.libraryTask.exceptions;

import com.maids.libraryTask.basis.BaseResponse;
import com.maids.libraryTask.utilities.LocaleUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final LocaleUtils localeUtils;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("MethodArgumentNotValidException: " + ex.getMessage());
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(BaseResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .validationErrors(errors)
                .message(localeUtils.getBundleMessage("exception.responses.MethodArgumentNotValidException"))
                .timestamp(LocalDateTime.now())
                .build()
                , HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
        log.error("ResourceNotFoundException: " + ex.getMessage());
        return new ResponseEntity<>(BaseResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(localeUtils.getBundleMessage("exception.responses.ResourceNotFoundException"))
                .exception(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build()
                , HttpStatus.BAD_REQUEST
        );
    }
}
