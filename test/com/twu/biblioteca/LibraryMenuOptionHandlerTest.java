package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

public class LibraryMenuOptionHandlerTest {

    LibraryMenuOptionHandler libraryMenuOptionHandler;
    IPrinter printerMock = Mockito.mock(IPrinter.class);
    IConsole consoleMock = Mockito.mock(IConsole.class);
    Library libraryMock = Mockito.mock(Library.class);

    @Before
    public void initialize(){
        libraryMenuOptionHandler = new LibraryMenuOptionHandler(printerMock, consoleMock);
    }

    @Test
    public void handleMenuOptionSelected_validBooksOption_printsBooks(){
        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(1);

        //assert
        Mockito.verify(printerMock, times(1));

    }

    @Test
    public void handleMenuOptionSelected_invalidOption_printsErrorMessage(){
        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(10);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("Please select a valid option!"));

    }

    @Test
    public void handleMenuOptionSelected_quitOptionSelected_printSeeYou(){
        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(10);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("See You!"));

    }

    @Test
    public void handleMenuOptionSelected_checkoutAvailableBook_printsSuccessMsg(){
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Narnia");
        Mockito.when(libraryMock.checkoutBook(anyString())).thenReturn(true);

        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(2);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("Thank you! Enjoy the book!"));

    }

    @Test
    public void handleMenuOptionSelected_checkoutNotAvailableBook_printsErrorMsg(){
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Divergent");
        Mockito.when(libraryMock.checkoutBook(anyString())).thenReturn(false);

        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(2);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("Sorry, that book is not available"));
    }
}
