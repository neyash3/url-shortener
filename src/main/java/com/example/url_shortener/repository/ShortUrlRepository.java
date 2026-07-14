package com.example.url_shortener.repository;
import com.example.url_shortener.models.ShortURL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShortUrlRepository extends MongoRepository<ShortURL,String> {
  //Used to find existing urls
  @Query(value = "{longURL: '?0'}")
  ShortURL findByUrl(String longUrl);
}
