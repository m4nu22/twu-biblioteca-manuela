package com.twu.biblioteca;
import com.twu.biblioteca.interfaces.IConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console implements IConsole {


    public int scan() throws InputMismatchException {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
