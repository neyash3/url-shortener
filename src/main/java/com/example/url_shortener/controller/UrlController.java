package com.example.url_shortener.controller;
import com.example.url_shortener.repository.ShortURL;
import com.example.url_shortener.services.UrlService;
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
    public ShortURL shorten (@RequestBody ShortenRequest request){
        return urlService.generateShortUrl(request.getLongUrl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> redirect(@PathVariable String id){
        return urlService.get(id);
    }
}
