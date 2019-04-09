package com.twu.biblioteca;

import com.twu.biblioteca.enums.MenuOptions;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;

import java.util.Arrays;
import java.util.List;


public class LibraryMenu{

    private IPrinter printer;
    private List<MenuOptions> menuOptions = Arrays.asList(MenuOptions.values());

    public LibraryMenu(IPrinter printer){
        this.printer = printer;
        printWelcomeMessage();
    }

    public void printWelcomeMessage(){
        printer.printLn("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void printMenuOfOptions(){
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
        }

        return name;

    }

    public static void handleMenuOptionSelected(int option, IPrinter printer) {
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

    private static void printBooks(IPrinter printer){
        List<Book> libraryBooks = Library.getInstance().getBooks();
        if(!libraryBooks.isEmpty())
            printer.printList(libraryBooks);
    }
}
