package com.twu.biblioteca.models;

import com.twu.biblioteca.enums.LibraryItemType;

public class Book extends LibraryItem {

    private String author;

    public Book(int id, String title, String author, String publicationYear) {
        super(id, title, publicationYear,LibraryItemType.book);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Title: " + this.getName() +
                " | Author: " + this.getAuthor() +
                " | Publication Year: " + this.getYear();
    }
}
