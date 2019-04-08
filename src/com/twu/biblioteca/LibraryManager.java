package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.List;

public class LibraryManager {

    private List<Book> books;

    public LibraryManager(List<Book> books){
        this.books = books;
    }

    public List<Book> getBooks(){
        return books;
    }
}
