package com.example.url_shortener.repository;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "url-shortener")
public class ShortURL {
    @Id
    private String id;

    private String longURL;

    public ShortURL(String id, String longURL){
        super();
        this.id = id;
        this.longURL = longURL;
    }

    //Getters and setters
    public String getId(){
        return this.id;
    }

    public String getLongURL(){
        return this.longURL;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }
}
