package com.apitesting.models;

import java.util.List;

public class BookResponse {

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private List<Book> books;


}
