package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;

public class Printer implements IPrinter {

    public void printLn(String text){
        System.out.println(text);
    }
}
