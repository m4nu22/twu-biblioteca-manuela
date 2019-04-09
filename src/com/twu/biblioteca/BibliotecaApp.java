package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import java.util.ArrayList;
import java.util.Arrays;

public class BibliotecaApp {

    public static void main(String[] args) {
        initializeLibrary();

        Printer printer = new Printer();
        LibraryMenu libraryMenu = new LibraryMenu(printer);
        libraryMenu.printWelcomeMessage();
        libraryMenu.ShowMenuAndHandleOptionSelection();
    }

    private static void initializeLibrary (){
        Library library = Library.getInstance();
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(hp,narnia));
        library.setBooks(books);
    }
}
