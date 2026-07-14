package com.example.url_shortener.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ShortenRequest {

  //longUrl can't be empty and can't be over 100 characters long
  @NotBlank(message = "longUrl is required")
  @Size(max = 100, message = "Length of Url must be less or equal to 100 characters")
  private String longUrl;

  @Size(min = 4, max = 16, message = "Length of custom Url must be greater or equal to 4 characters and less or equal to 16 characters")
  private String customUrl;

  public ShortenRequest(String longUrl, String customUrl){
    this.longUrl = longUrl;
    this.customUrl = customUrl;
  }

  public String getLongUrl() {
    return longUrl;
  }
  public String getCustomUrl(){ return customUrl; }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }
  public void setCustomUrl(String customUrl){ this.customUrl = customUrl; }
}
