package com.example.url_shortener.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ShortenRequest {

  //longUrl can't be empty and can't be over 100 characters long
  @NotBlank(message = "longUrl is required")
  @Size(max = 100, message = "Length of Url must be less or equal to 100 characters")
  @Pattern(regexp = "^(https?://)?([A-z0-9]{2,6}\\.[A-z0-9]{1,63}\\.[A-z]{2,6})(/?[A-z0-9/-]+)?$", message = "longUrl must be a valid Url")
  private String longUrl;

  @Size(min = 4, max = 16, message = "Length of custom Url must be greater or equal to 4 characters and less or equal to 16 characters")
  @Pattern(regexp = "^[A-z0-9\\-]{4,16}$", message = "customUrl must only contain alphanumeric characters and '-'")
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
