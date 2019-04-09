package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.models.Book;

import java.util.List;

public interface ILibrary {

    static Library getInstance() {
        return null;
    }

    List<Book> getAvailableBooks();

    void setBooks(List<Book> books);

    boolean checkoutBook(String title);

    boolean returnBook(String title);
}
