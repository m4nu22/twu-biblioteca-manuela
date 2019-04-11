package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.ILibrary;
import com.twu.biblioteca.interfaces.IMenuOptionHandler;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.User;

import java.util.List;

public class CustomerMenuOptionHandler implements IMenuOptionHandler {

    private IPrinter printer;
    private IConsole console;
    private ILibrary library;
    private User user;


    public CustomerMenuOptionHandler(IPrinter printer, IConsole console, ILibrary library, User user) {
        this.printer = printer;
        this.console = console;
        this.library = library;
        this.user = user;
    }

    public void handleMenuOptionSelected(int option) {
        switch (option) {
            case 0:
                printer.printLn("See you!");
                break;
            case 1:
                printAvailableItems(LibraryItemType.BOOK);
                break;
            case 2:
                checkoutItem(LibraryItemType.BOOK);
                break;
            case 3:
                returnBook();
                break;
            case 4:
                printAvailableItems(LibraryItemType.MOVIE);
                break;
            case 5:
                checkoutItem(LibraryItemType.MOVIE);
                break;
            case 6:
                printMyInfo();
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
        String msg = String.format("Please type the name of the %s you want to checkout", type.toString().toLowerCase());
        String title = printMsgAndReturnConsoleResponse(msg);

        boolean couldCheckout = library.checkoutItem(title, type, user.getLibraryNumber());

        printMsgByCondition(couldCheckout,
                String.format("Thank you! Enjoy the %s!", type.toString().toLowerCase()),
                String.format("Sorry, that %s is not available", type.toString().toLowerCase()));
    }

    private void returnBook() {
        String bookTitle = printMsgAndReturnConsoleResponse("Please type the name of the book you want to return");

        boolean couldReturn = library.returnBook(bookTitle, user.getLibraryNumber());

        printMsgByCondition(couldReturn, "Thank you for returning the book", "That is not a valid book to return");
    }

    private String printMsgAndReturnConsoleResponse(String msg) {
        printer.printLn(msg);
        return console.readString();
    }

    private void printMsgByCondition(boolean condition, String successMsg, String errorMsg) {
        if (condition)
            printer.printLn(successMsg);
        else
            printer.printLn(errorMsg);
    }

    private void printMyInfo(){
        printer.printLn(user.toString());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
