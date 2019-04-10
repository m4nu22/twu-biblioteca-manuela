package com.twu.biblioteca.interfaces;

import java.util.List;
import java.util.Map;

public interface IPrinter {

    void printLn(String text);

    <T> void printList(List<T> list);

    void printLn(Map<String, String> customerItemMap);
}
