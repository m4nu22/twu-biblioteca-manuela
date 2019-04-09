package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;

import java.util.List;

public class LibraryMenuOptionHandler {

    private IPrinter printer;
    private IConsole console;

    public LibraryMenuOptionHandler(IPrinter printer, IConsole console){
        this.printer = printer;
        this.console = console;
    }

    public void handleMenuOptionSelected(int option) {
        switch(option){
            case 0:
                printer.printLn("See you!");
                break;
            case 1:
                printAvailableBooks();
                break;
            case 2:
                checkoutBook();
                break;
            case 3:
                returnBook();
                break;
            default:
                printer.printLn("Please select a valid option!");
                break;
        }
    }

    private void printAvailableBooks(){
        List<Book> libraryBooks = Library.getInstance().getAvailableBooks();
        if(!libraryBooks.isEmpty())
            printer.printList(libraryBooks);
    }

    private void checkoutBook(){
        printer.printLn("Please type the name of the book you want to checkout");
        String bookTitle = console.readString();

        Library library = Library.getInstance();
        boolean couldCheckout = library.checkoutBook(bookTitle);
        printCheckoutMessage(couldCheckout);
    }

    private void printCheckoutMessage(boolean couldCheckout) {
        if(couldCheckout)
            printer.printLn("Thank you! Enjoy the book!");
        else
            printer.printLn("Sorry, that book is not available");
    }

    private void returnBook(){
        printer.printLn("Please type the name of the book you want to return");
        String bookTitle = console.readString();

        Library library = Library.getInstance();
        boolean couldReturn = library.returnBook(bookTitle);
        //printCheckoutMessage(couldReturn);
    }
}
