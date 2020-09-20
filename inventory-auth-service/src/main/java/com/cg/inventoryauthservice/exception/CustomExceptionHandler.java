/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 01:36:37
 * @modify date 2020-09-20 01:36:37
 * @desc Defines all exception handler
 */
package com.cg.inventoryauthservice.exception;

import java.util.HashMap;
import java.util.Map;

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

  /**
   * Validation Response
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleErrorResponse(UserNotFoundException exception) {
    ErrorResponse response = new ErrorResponse();
    response.setStatus(HttpStatus.NOT_FOUND.value());
    response.setMessage(exception.getMessage());
    response.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> catchAllException(Exception exception) {
    ErrorResponse response = new ErrorResponse();
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setMessage(exception.getMessage());
    response.setTimeStamp(System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
