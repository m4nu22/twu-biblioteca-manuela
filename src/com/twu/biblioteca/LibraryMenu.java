package com.twu.biblioteca;

import com.twu.biblioteca.enums.CustomerMenuOptions;
import com.twu.biblioteca.enums.LibrarianMenuOptions;
import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IMenuOptionHandler;
import com.twu.biblioteca.interfaces.IPrinter;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;


public class LibraryMenu {

    private IPrinter printer;
    private IConsole console;
    private IMenuOptionHandler handler;
    private List<CustomerMenuOptions> customerMenuOptions = Arrays.asList(CustomerMenuOptions.values());
    private List<LibrarianMenuOptions> librarianMenuOptions = Arrays.asList(LibrarianMenuOptions.values());

    public LibraryMenu(IPrinter printer, IConsole console, IMenuOptionHandler handler) {
        this.printer = printer;
        this.console = console;
        this.handler = handler;
    }

    public void printWelcomeMessage() {
        printer.printLn("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void showMenuAndHandleOptionSelection(UserRole role) {
        boolean shouldQuit = false;

        while (!shouldQuit) {
            printMenuOfOptions(role);
            try {
                int option = console.readInt();
                handler.handleMenuOptionSelected(option);

                if (option == 0)
                    shouldQuit = true;

            } catch (InputMismatchException e) {
                printer.printLn("Please select a valid option!");
            }
        }
    }

    private void printMenuOfOptions(UserRole userRole) {
        printer.printLn("\nLibrary Menu Options:");

        String options = "";

        if(UserRole.CUSTOMER.equals(userRole)) {
            for (CustomerMenuOptions opt : customerMenuOptions) {
                options += (opt.getDescription()) + "\n";
            }
        }else{
            for (LibrarianMenuOptions opt : librarianMenuOptions) {
                options += (opt.getDescription()) + "\n";
            }
        }

        printer.printLn(options);
    }
}
