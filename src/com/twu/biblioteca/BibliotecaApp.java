package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        Printer printer = new Printer();

        printWelcomeMsg(printer);

        List<Book> books = initializeLibrary();

        LibraryManager libraryManager = new LibraryManager(books);

        printBooks(libraryManager, printer);

    }

    private static void printWelcomeMsg(Printer printer){
        LibraryMenu libraryMenu = new LibraryMenu(printer);
        libraryMenu.printWelcomeMessage();
    }

    private static List<Book> initializeLibrary (){
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(hp,narnia));
    }

    private static void printBooks(LibraryManager libraryManager, Printer printer){
        List<Book> libraryBooks = libraryManager.getBooks();
        printer.printList(libraryBooks);
    }
}
