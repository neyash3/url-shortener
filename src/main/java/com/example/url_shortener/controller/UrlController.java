package com.example.url_shortener.controller;
import com.example.url_shortener.exceptions.NotFoundException;
import com.example.url_shortener.models.ShortenRequest;
import com.example.url_shortener.services.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UrlController {
  @Autowired
  UrlService urlService;

  @ResponseBody
  @PostMapping("/")
  public ResponseEntity<Object> shorten (@Valid @RequestBody ShortenRequest request){
    String longUrl = request.getLongUrl();
    String customUrl = request.getCustomUrl();
    if(customUrl == null||customUrl.isEmpty()){
      return urlService.generateShortUrl(longUrl);
    }else{
      return urlService.generateShortUrl(longUrl, customUrl);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> redirect(@PathVariable String id) throws NotFoundException {
    return urlService.getRedirect(id);
  }

  @GetMapping("/{id}/analytics")
  public ResponseEntity<Object> getAnalytics(@PathVariable String id) throws NotFoundException {
    return urlService.getUrl(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable String id){
    return urlService.deleteUrl(id);
  }
}
