package com.twu.biblioteca;

import com.twu.biblioteca.enums.MenuOptions;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;


public class LibraryMenu{

    private IPrinter printer;
    private List<MenuOptions> menuOptions = Arrays.asList(MenuOptions.values());

    public LibraryMenu(IPrinter printer){
        this.printer = printer;
    }

    public void printWelcomeMessage(){
        printer.printLn("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void ShowMenuAndHandleOptionSelection() {
        IConsole console = new Console();
        boolean shouldQuit = false;

        LibraryMenuOptionHandler handler = new LibraryMenuOptionHandler(printer, console);

        while(!shouldQuit) {
            printMenuOfOptions();
            try {
                int option = console.readInt();
                handler.handleMenuOptionSelected(option);

                if(option == 0)
                    shouldQuit = true;

            }catch(InputMismatchException e){
                printer.printLn("Please select a valid option!");
            }

        }
    }

    private void printMenuOfOptions(){
        printer.printLn("\nLibrary Menu Options:");
        for (MenuOptions opt: menuOptions) {
            String optionName = getMenuOptionNameFromEnum(opt);
            if(!optionName.equals(""))
                printer.printLn(optionName);
        }
    }

    private String getMenuOptionNameFromEnum(MenuOptions opt){
        String name = "";

        switch(opt){
            case quit:
                name = "0 - Quit";
                break;
            case listBooks:
                name = "1 - List of books";
                break;
            case checkoutBook:
                name = "2 - Checkout Book";
                break;
        }

        return name;

    }
}
