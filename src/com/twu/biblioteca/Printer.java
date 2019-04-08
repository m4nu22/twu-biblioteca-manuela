package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;

import java.util.List;

public class Printer implements IPrinter {

    public void printLn(String text){
        System.out.println(text);
    }

    public <T> void printList(List<T> list){
        for (T item: list) {
            System.out.println(item);
        }
    }
}
