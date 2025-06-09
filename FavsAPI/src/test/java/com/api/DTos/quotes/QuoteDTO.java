package com.api.DTos.quotes;


public class QuoteDTO {
    public String author;
    public String body;

    public QuoteDTO(){

    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
