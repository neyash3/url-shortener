package com.example.url_shortener.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ShortenRequest {

  //longUrl can't be empty and can't be over 100 characters long
  @NotBlank(message = "longUrl is required")
  @Size(max = 100, message = "Length of Url must be less or equal to 100 characters")
  private String longUrl;

  public ShortenRequest(String longUrl){
    this.longUrl = longUrl;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }
}
