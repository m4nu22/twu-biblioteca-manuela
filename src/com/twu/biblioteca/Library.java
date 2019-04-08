package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.List;

public class Library {

    private List<Book> books;
    private static Library instance;

    private Library(){}

    public static Library getInstance(){
        if(instance == null)
            instance = new Library();

        return instance;
    }

    public void init(List<Book> books){
        this.books = books;
    }

    public List<Book> getBooks(){
        return books;
    }
}
