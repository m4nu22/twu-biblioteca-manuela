package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class BibliotecaApp {

    public static void main(String[] args) {
        initializeLibrary();
        ShowMenuAndHandleOptionSelection();
    }

    private static void initializeLibrary (){
        Library library = Library.getInstance();
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(hp,narnia));
        library.setBooks(books);
    }

    private static void ShowMenuAndHandleOptionSelection() {
        Printer printer = new Printer();
        LibraryMenu libraryMenu = new LibraryMenu(printer);

        IConsole console = new Console();
        boolean shouldQuit = false;

        while(!shouldQuit) {
            libraryMenu.printMenuOfOptions();
            try {
                int option = console.scan();
                libraryMenu.handleMenuOptionSelected(option, printer);

                if(option == 0)
                    shouldQuit = true;

            }catch(InputMismatchException e){
                printer.printLn("Please select a valid option!");
            }

        }
    }
}
