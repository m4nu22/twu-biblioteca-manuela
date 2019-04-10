package com.twu.biblioteca;

import com.twu.biblioteca.enums.MenuOptions;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.ILibraryMenuOptionHandler;
import com.twu.biblioteca.interfaces.IPrinter;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;


public class LibraryMenu {

    private IPrinter printer;
    private IConsole console;
    private ILibraryMenuOptionHandler handler;
    private List<MenuOptions> menuOptions = Arrays.asList(MenuOptions.values());

    public LibraryMenu(IPrinter printer, IConsole console, ILibraryMenuOptionHandler handler) {
        this.printer = printer;
        this.console = console;
        this.handler = handler;
    }

    public void printWelcomeMessage() {
        printer.printLn("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void showMenuAndHandleOptionSelection() {
        boolean shouldQuit = false;

        while (!shouldQuit) {
            printMenuOfOptions();
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

    private void printMenuOfOptions() {
        printer.printLn("\nLibrary Menu Options:");
        for (MenuOptions opt : menuOptions) {
                printer.printLn(opt.getDescription());
        }
    }
}
