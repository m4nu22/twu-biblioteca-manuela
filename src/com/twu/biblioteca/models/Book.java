package com.twu.biblioteca.models;

public class Book {

    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private boolean isCheckedOut;

    public Book(int id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isCheckedOut = false;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public boolean isCheckedOut() { return isCheckedOut; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setCheckedOut(boolean checkedOut) { this.isCheckedOut = checkedOut; }

    @Override
    public String toString() {
        return "Title: " + this.title +
                " | Author: " + this.author +
                " | Publication Year: " + this.publicationYear;
    }
}
