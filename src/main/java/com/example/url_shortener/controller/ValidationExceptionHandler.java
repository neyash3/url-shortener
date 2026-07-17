package com.example.url_shortener.controller;

import com.example.url_shortener.models.InvalidResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ValidationExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException nve){
    return new ResponseEntity<Object>(new InvalidResponse(400, nve), HttpStatus.BAD_REQUEST);
  }
}
