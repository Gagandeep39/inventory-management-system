/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 01:36:37
 * @modify date 2020-09-20 01:36:37
 * @desc Defines all exception handler
 */
package com.cg.inventoryauthservice.exception;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.cg.inventoryauthservice.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class CustomExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .body(ex.getBindingResult()
      .getAllErrors()
      .stream()
      .collect(Collectors.toMap(
        (error) -> ((FieldError) error).getField(), 
        (error) -> error.getDefaultMessage(), 
        (error, duplicateError) -> error)));
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidCredentialException.class)
  public ResponseEntity<Map<String, String>> handleInvalidCredential(InvalidCredentialException ex) {
    return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .body(Collections.singletonMap(ex.getErrorName(), ex.getErrorDescription()));
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException exception) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(ErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .message(exception.getMessage())
        .timeStamp(System.currentTimeMillis())
        .build());
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> catchAllException(Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .message(exception.getMessage())
        .timeStamp(System.currentTimeMillis())
        .build());
  }

}
