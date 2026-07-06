package com.example.url_shortener.services;
import com.example.url_shortener.repository.ShortUrlRepository;
import com.example.url_shortener.repository.ShortURL;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service
public class UrlService {
    private final ShortUrlRepository shortUrlRepository;

    public UrlService(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortURL generateShortUrl(String longUrl){
        var tmpUrl = shortUrlRepository.findByUrl(longUrl);
        if (tmpUrl == null){
            ShortURL shortUrl = new ShortURL(generateId(), longUrl);
            return shortUrlRepository.save(shortUrl);
        } else {
            return tmpUrl;
        }
    }

    public ResponseEntity<Object> get(String id) {
        return shortUrlRepository.findById(id)
                .map(shortURL -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .location(URI.create(shortURL.getLongURL()))
                        .build())
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    private String generateId() {
        String id;
        do {
            id = RandomStringUtils.secure().nextAlphanumeric(8);
        } while (shortUrlRepository.existsById(id));
        return id;
    }
}
