package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;

public class BibliotecaApp {

    public static void main(String[] args) {
        initializeLibrary();

        Printer printer = new Printer();
        Console console = new Console();
        LibraryMenuOptionHandler handler = new LibraryMenuOptionHandler(printer, console, Library.getInstance());

        LibraryMenu libraryMenu = new LibraryMenu(printer, console, handler);
        libraryMenu.printWelcomeMessage();

        libraryMenu.ShowMenuAndHandleOptionSelection();
    }

    private static void initializeLibrary() {
        Library library = Library.getInstance();

        ArrayList<Book> books = AddBooksToLibrary();
        library.setBooks(books);

        ArrayList<Movie> movies = AddMoviesToLibrary();
        library.setMovies(movies);
    }

    private static ArrayList<Book> AddBooksToLibrary() {
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(hp, narnia));
    }

    private static ArrayList<Movie> AddMoviesToLibrary() {
        Movie memento = new Movie(1, "Memento", "2000", "Christopher Nolan", 10);
        Movie contratiempo = new Movie(2, "Contratiempo", "2017", "Oriol Paulo", 10);
        Movie aStarIsBorn = new Movie(2, "A star is born", "2018", "Bradley Cooper", 8);
        return new ArrayList<>(Arrays.asList(memento, contratiempo,aStarIsBorn));
    }
}
