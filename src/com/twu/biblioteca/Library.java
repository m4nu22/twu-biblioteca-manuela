package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;
    private static Library instance;

    private Library(){}

    public static Library getInstance(){
        if(instance == null)
            instance = new Library();

        return instance;
    }

    public List<Book> getAvailableBooks(){
        return books.stream().filter(b -> !b.isCheckedOut()).collect(Collectors.toList());
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    public boolean checkoutBook(String title){
        boolean canCheckout = false;

        Book book = books.stream().filter(b -> title.equalsIgnoreCase(b.getTitle()) && !b.isCheckedOut()).findFirst().orElse(null);

        if(book != null) {
            canCheckout = true;
            book.setCheckedOut(true);
        }

        return canCheckout;
    }
}
