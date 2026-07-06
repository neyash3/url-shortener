package com.example.url_shortener.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShortUrlRepository extends MongoRepository<ShortURL,String> {
    @Query(value = "{longURL: '?0'}")
    ShortURL findByUrl(String longUrl);
}
