package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        library.init(books);
    }

    private static void ShowMenuAndHandleOptionSelection() {
        Printer printer = new Printer();
        LibraryMenu libraryMenu = new LibraryMenu(printer);

        libraryMenu.printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        while(true) {
            libraryMenu.printMenuOfOptions();
            int option = in.nextInt();
            libraryMenu.handleMenuOptionSelected(option, printer);
        }
    }
}
