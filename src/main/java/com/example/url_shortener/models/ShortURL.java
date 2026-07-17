package com.example.url_shortener.models;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "url-shortener")
public class ShortURL {
  @Id
  private String id;
  private String longURL;
  private int visits;
  private final LocalDateTime timeOfCreation;

  public ShortURL(String id, String longURL, LocalDateTime timeOfCreation){
    super();
    this.id = id;
    this.longURL = longURL;
    this.visits = 0;
    this.timeOfCreation = timeOfCreation;
  }

  //Getters and setters
  public String getId(){
    return this.id;
  }
  public String getLongURL(){
    return this.longURL;
  }
  public int getVisits(){
    return this.visits;
  }
  public LocalDateTime getTimeOfCreation() {
    return timeOfCreation;
  }

  public void setId(String id){
    this.id = id;
  }
  public void setLongURL(String longURL) {
    this.longURL = longURL;
  }
  public void setVisits(int visits){
    this.visits = visits;
  }
}
