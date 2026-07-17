package com.example.url_shortener.services;
import com.example.url_shortener.models.InvalidResponse;
import com.example.url_shortener.repository.ShortUrlRepository;
import com.example.url_shortener.models.ShortURL;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service
public class UrlService {
  private final ShortUrlRepository shortUrlRepository;

  public UrlService(ShortUrlRepository shortUrlRepository) {
    this.shortUrlRepository = shortUrlRepository;
  }

  public ResponseEntity<Object> generateShortUrl(String longUrl) {
    ShortURL shortUrl;

    var tmpUrl = shortUrlRepository.findByUrl(longUrl);

    shortUrl = tmpUrl == null ? shortUrlRepository.save(new ShortURL(generateId(), longUrl)) : tmpUrl;

    return new ResponseEntity<>(shortUrl, HttpStatus.OK);
  }

  public ResponseEntity<Object> generateShortUrl(String longUrl, String customUrl) {
    var tmpUrl = shortUrlRepository.findById(customUrl);

    if (tmpUrl.isPresent()) {
      return new ResponseEntity<>(new InvalidResponse(400, new Exception("Custom Url already exists")), HttpStatus.BAD_REQUEST);
    }

    ShortURL shortURL = shortUrlRepository.save(new ShortURL(customUrl, longUrl));
    return new ResponseEntity<>(shortURL, HttpStatus.OK);
  }

  public ResponseEntity<Object> get(String id) {
    return shortUrlRepository.findById(id)
        .map(shortURL -> ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(shortURL.getLongURL()))
            .build())
        .orElse(ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new InvalidResponse(404, new Exception("Provided id could not be found"))));
  }

  private String generateId() {
    String id;
    do {
      id = RandomStringUtils.secure().nextAlphanumeric(8);
    } while (shortUrlRepository.existsById(id));
    return id;
  }
}
