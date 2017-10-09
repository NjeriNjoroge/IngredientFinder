package com.example.gnjoroge.ingredientfinder.model;

import org.parceler.Parcel;

/**
 * Created by gnjoroge on 10/8/17.
 */


public class Post {

    String title;
    String body;
    String author;

    public Post() {}

    public Post(String title, String body, String author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }


}
