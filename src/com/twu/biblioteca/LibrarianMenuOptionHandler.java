package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.ILibrary;
import com.twu.biblioteca.interfaces.ILibraryMenuOptionHandler;
import com.twu.biblioteca.interfaces.IPrinter;

import java.util.Map;

public class LibrarianMenuOptionHandler implements ILibraryMenuOptionHandler {

    private IPrinter printer;
    private ILibrary library;

    public LibrarianMenuOptionHandler(Printer printer, Library library){
        this.printer = printer;
        this.library = library;
    }
    public void handleMenuOptionSelected(int option) {
        switch(option){
            case 0:
                printer.printLn("See you");
                break;
            case 1:
                printCheckedOutBooks();
                break;
            default:
                printer.printLn("Please select a valid option!");
                break;
        }
    }

    private void printCheckedOutBooks() {
        Map<String, String> customerItemMap = library.getCustomerItemMap();
        printer.printLn("Library Item Name - User ID Number");
        printer.printLn(customerItemMap);
    }
}
