package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;

import java.util.List;

public class LibraryMenuOptionHandler {

    private IPrinter printer;

    public LibraryMenuOptionHandler(IPrinter printer){
        this.printer = printer;
    }

    public void handleMenuOptionSelected(int option) {
        switch(option){
            case 0:
                printer.printLn("See you!");
                break;
            case 1:
                printBooks(printer);
                break;
            default:
                printer.printLn("Please select a valid option!");
                break;
        }
    }

    private void printBooks(IPrinter printer){
        List<Book> libraryBooks = Library.getInstance().getBooks();
        if(!libraryBooks.isEmpty())
            printer.printList(libraryBooks);
    }
}
