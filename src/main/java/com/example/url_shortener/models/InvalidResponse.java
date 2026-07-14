package com.example.url_shortener.models;
import java.time.LocalDateTime;

public class InvalidResponse {
  private int status;
  private String message;
  private LocalDateTime time;

  public InvalidResponse(int status, String message){
    this.status = status;
    this.message = message;
    this.time = LocalDateTime.now();
  }

  public int getStatus(){return status;}
  public String getMessage(){return message;}
  public LocalDateTime getTime(){return time;}

  public void setStatus(int status){this.status = status;}
  public void setMessage(String message){this.message = message; }
  public void setTime(LocalDateTime time){this.time = time; }

}
