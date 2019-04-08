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
    }

    public void printWelcomeMessage(){
        printer.printLn("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void printMenuOfOptions(){
        for (MenuOptions opt: menuOptions) {
            String optionName = getMenuOptionNameFromEnum(opt);
            if(!optionName.equals(""))
                printer.printLn(optionName);
        }
    }

    private String getMenuOptionNameFromEnum(MenuOptions opt){
        String name = "";

        switch(opt){
            case listBooks:
                name = "1 - List of books";
                break;
        }

        return name;

    }

    public static void handleMenuOptionSelected(int option, Printer printer) {
        switch(option){
            case 1:
                printBooks(printer);
                break;
        }
    }

    private static void printBooks(Printer printer){
        List<Book> libraryBooks = Library.getInstance().getBooks();
        if(!libraryBooks.isEmpty())
            printer.printList(libraryBooks);
    }
}
