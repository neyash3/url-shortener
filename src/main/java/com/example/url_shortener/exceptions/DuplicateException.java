package com.example.url_shortener.exceptions;

public class DuplicateException extends RuntimeException {
  public DuplicateException(String message) {
    super(message);
  }
}
