package com.example.url_shortener.services;
import com.example.url_shortener.models.InvalidResponse;
import com.example.url_shortener.repository.ShortUrlRepository;
import com.example.url_shortener.models.ShortURL;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.regex.Pattern;

@Service
public class UrlService {
  private final ShortUrlRepository shortUrlRepository;

  public UrlService(ShortUrlRepository shortUrlRepository){
    this.shortUrlRepository = shortUrlRepository;
  }

//    public ShortURL generateShortUrl(String longUrl){
//        var tmpUrl = shortUrlRepository.findByUrl(longUrl);
//        if (tmpUrl == null){
//            ShortURL shortUrl = new ShortURL(generateId(), longUrl);
//            return shortUrlRepository.save(shortUrl);
//        } else {
//            return tmpUrl;
//        }
//    }

  //Replaced return type with ResponseEntity for consistency
  public ResponseEntity<Object> generateShortUrl(String longUrl){
    ShortURL shortUrl;

    //Returns an error message when url fails validation
    if(!isValidUrl(longUrl)) return new ResponseEntity<>(new InvalidResponse(400, "URL failed validation"), HttpStatus.BAD_REQUEST);

    var tmpUrl = shortUrlRepository.findByUrl(longUrl);

    shortUrl = tmpUrl == null ? new ShortURL(generateId(), longUrl) : tmpUrl;
    return new ResponseEntity<>(shortUrl,HttpStatus.OK);
  }

  public ResponseEntity<Object> get(String id) {
    return shortUrlRepository.findById(id)
        .map(shortURL -> ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(shortURL.getLongURL()))
            .build())
        .orElse(ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new InvalidResponse(404, "Provided id could not be found")));
  }

  private String generateId() {
    String id;
    do {
      id = RandomStringUtils.secure().nextAlphanumeric(8);
    } while (shortUrlRepository.existsById(id));
    return id;
  }

  private boolean isValidUrl(String longUrl){
    Pattern pattern = Pattern.compile("^(https?://)?([A-z0-9]{2,6}\\.[A-z0-9]{1,63}\\.[A-z]{2,6})(/?[A-z0-9/-]+)?$");
    return pattern.matcher(longUrl).matches();
  }
}
