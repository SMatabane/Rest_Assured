package com.apitesting.models;

public class ReplaceIsbn {

    private String userId;
    private String isbn;


    public ReplaceIsbn() {
    }

    public ReplaceIsbn(String userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
