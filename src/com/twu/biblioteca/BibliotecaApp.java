package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<LibraryItem> items = AddItemsToLibrary();

        library.setItems(items);
    }

    private static ArrayList<LibraryItem> AddItemsToLibrary() {
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        Movie memento = new Movie(1, "Memento", "2000", "Christopher Nolan", 10);
        Movie contratiempo = new Movie(2, "Contratiempo", "2017", "Oriol Paulo");
        Movie aStarIsBorn = new Movie(2, "A star is born", "2018", "Bradley Cooper", 8);
        return new ArrayList<>(Arrays.asList(hp, narnia, memento, contratiempo,aStarIsBorn));
    }
}
