package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;

import java.util.List;
import java.util.Map;

public class Printer implements IPrinter {

    public void printLn(String text) {
        System.out.println(text);
    }

    public <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

    public void printLn(Map<String, String> customerItemMap) {
        for (Map.Entry<String, String> entry : customerItemMap.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            printLn(key + " - " + val);
        }
    }
}
