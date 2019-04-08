package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class LibraryMenuTest {

    LibraryMenu libraryMenu;
    IPrinter printerMock = Mockito.mock(IPrinter.class);

    @Before
    public void initialize(){
        libraryMenu = new LibraryMenu(printerMock);
    }

    @Test
    public void printWelcomeMessage() {

        //act
        libraryMenu.printWelcomeMessage();

        //assert
        Mockito.verify(printerMock,times(1)).printLn(ArgumentMatchers.eq("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));

    }
}
