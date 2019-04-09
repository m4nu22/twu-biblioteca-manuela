package com.twu.biblioteca.interfaces;

import java.util.InputMismatchException;

public interface IConsole {
    int readInt() throws InputMismatchException;
    String readString();
}
