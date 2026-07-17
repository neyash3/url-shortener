package com.example.url_shortener.repository;
import com.example.url_shortener.models.ShortURL;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ShortUrlRepository extends MongoRepository<ShortURL,String> {
}
