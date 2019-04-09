package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void handleMenuOptionSelected_validBooksOption_printsBooks(){
        //act
        libraryMenu.handleMenuOptionSelected(1, printerMock);

        //assert
        Mockito.verify(printerMock, times(1));

    }

    @Test
    public void handleMenuOptionSelected_invalidOption_printsErrorMessage(){
        //act
        libraryMenu.handleMenuOptionSelected(10, printerMock);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("Please select a valid option!"));

    }

    @Test
    public void handleMenuOptionSelected_quitOptionSelected_printSeeYou(){
        //act
        libraryMenu.handleMenuOptionSelected(10, printerMock);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("See You!"));

    }
}
