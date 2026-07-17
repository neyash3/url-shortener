package com.example.url_shortener.models;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;

public class InvalidResponse {
  private int status;
  private HashMap<String, String> exception;
  private LocalDateTime time;


  public InvalidResponse(int status, Exception exc){
    this.status = status;
    this.exception = new HashMap<>();
    this.exception.put("name",exc.getClass().getName());
    this.exception.put("message",exc.getMessage());
    this.time = LocalDateTime.now();
  }

  //Constructor called specifically when the Spring exception handler throws a validation exception
  public InvalidResponse(int status, MethodArgumentNotValidException exc){
    this.status = status;
    this.exception = new HashMap<>();
    exception.put("name", exc.getClass().getName());
    exc.getBindingResult().getAllErrors().forEach(error -> {
      exception.put(((FieldError)error).getField(), ((FieldError)error).getDefaultMessage());
    });
  }

  public int getStatus(){return status;}
  public HashMap<String, String> getException(){return exception;}
  public LocalDateTime getTime(){return time;}

  public void setStatus(int status){this.status = status;}
  public void setException(HashMap<String, String> error){ this.exception = error; }
  public void setTime(LocalDateTime time){this.time = time; }

}
