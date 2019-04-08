package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;

public class LibraryMenu{

    private IPrinter printer;

    public LibraryMenu(IPrinter printer){
        this.printer = printer;
    }

    public void printWelcomeMessage(){
        printer.printLn("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }
}
