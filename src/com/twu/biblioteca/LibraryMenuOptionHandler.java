package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.ILibrary;
import com.twu.biblioteca.interfaces.ILibraryMenuOptionHandler;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;

import java.util.List;

public class LibraryMenuOptionHandler implements ILibraryMenuOptionHandler {

    private IPrinter printer;
    private IConsole console;
    private ILibrary library;

    public LibraryMenuOptionHandler(IPrinter printer, IConsole console, ILibrary library) {
        this.printer = printer;
        this.console = console;
        this.library = library;
    }

    public void handleMenuOptionSelected(int option) {
        switch (option) {
            case 0:
                printer.printLn("See you!");
                break;
            case 1:
                printAvailableItems(LibraryItemType.book);
                break;
            case 2:
                checkoutItem(LibraryItemType.book);
                break;
            case 3:
                returnBook();
                break;
            case 4:
                printAvailableItems(LibraryItemType.movie);
                break;
            case 5:
                checkoutItem(LibraryItemType.movie);
                break;
            default:
                printer.printLn("Please select a valid option!");
                break;
        }
    }

    private void printAvailableItems(LibraryItemType type) {
        List<LibraryItem> libraryBooks = library.getAvailableItemsPerType(type);
        if (!libraryBooks.isEmpty())
            printer.printList(libraryBooks);
    }

    private void checkoutItem(LibraryItemType type) {
        String msg = String.format("Please type the name of the %s you want to checkout", type.toString());
        printer.printLn(msg);
        String title = console.readString();

        boolean couldCheckout = library.checkoutItem(title,type);
        printCheckoutMessage(couldCheckout, type);
    }


    private void printCheckoutMessage(boolean couldCheckout, LibraryItemType type) {
        if (couldCheckout)
            printer.printLn(String.format("Thank you! Enjoy the %s!",type.toString()));
        else
            printer.printLn(String.format("Sorry, that %s is not available",type.toString()));
    }

    private void returnBook() {
        printer.printLn("Please type the name of the book you want to return");
        String bookTitle = console.readString();

        boolean couldReturn = library.returnBook(bookTitle);
        printReturnBookMessage(couldReturn);
    }

    private void printReturnBookMessage(boolean couldReturn) {
        if (couldReturn)
            printer.printLn("Thank you for returning the book");
        else
            printer.printLn("That is not a valid book to return");
    }
}
