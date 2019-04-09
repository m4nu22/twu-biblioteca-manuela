package com.twu.biblioteca.interfaces;

import java.util.List;

public interface IPrinter {

    void printLn(String text);

    <T> void printList(List<T> list);
}
