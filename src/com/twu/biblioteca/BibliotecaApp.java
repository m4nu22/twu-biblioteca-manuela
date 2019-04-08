package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;

public class BibliotecaApp {

    public static void main(String[] args) {

        IPrinter printer = new Printer();
        LibraryMenu libraryMenu = new LibraryMenu(printer);
        libraryMenu.printWelcomeMessage();
    }
}
