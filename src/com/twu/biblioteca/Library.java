package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.ILibrary;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class Library implements ILibrary {

    private List<Book> books;
    private List<Movie> movies;

    private static Library instance;

    private Library() {}

    public static Library getInstance() {
        if (instance == null)
            instance = new Library();

        return instance;
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(b -> !b.isCheckedOut()).collect(Collectors.toList());
    }

    public List<Movie> getAvailableMovies(){
        return movies.stream().filter(b -> !b.isCheckedOut()).collect(Collectors.toList());
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean checkoutBook(String title) {
        boolean canCheckout = false;

        Book book = books.stream().filter(b -> title.equalsIgnoreCase(b.getName()) && !b.isCheckedOut()).findFirst().orElse(null);

        if (book != null) {
            canCheckout = true;
            book.setCheckedOut(true);
        }

        return canCheckout;
    }

    public boolean returnBook(String title) {
        boolean canReturn = false;

        Book book = books.stream().filter(b -> title.equalsIgnoreCase(b.getName()) && b.isCheckedOut()).findFirst().orElse(null);

        if (book != null) {
            canReturn = true;
            book.setCheckedOut(false);
        }

        return canReturn;
    }
}
