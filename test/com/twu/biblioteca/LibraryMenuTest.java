package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.InputMismatchException;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.never;
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

    public void ShowMenuAndHandleOptionSelection_invalidOption_printsError(){

        //arrange
        IConsole consoleMock = Mockito.mock(IConsole.class);
        Mockito.when(consoleMock.readInt()).thenThrow(new InputMismatchException());

        //act && assert
        try {
            libraryMenu.ShowMenuAndHandleOptionSelection();
            fail();
        }catch(InputMismatchException e){
            Mockito.verify(printerMock,times(1)).printLn(ArgumentMatchers.eq("Please select a valid option!"));
        }
    }

    public void ShowMenuAndHandleOptionSelection_validOption_doesntThrowError(){

        //arrange
        IConsole consoleMock = Mockito.mock(IConsole.class);
        Mockito.when(consoleMock.readInt()).thenReturn(1);

        //act
        libraryMenu.ShowMenuAndHandleOptionSelection();

        //assert
        Mockito.verify(printerMock, never()).printLn(ArgumentMatchers.eq("Please select a valid option!"));

    }

    public void ShowMenuAndHandleOptionSelection_invalidNumberOption_printsErrorMsg(){

        //arrange
        IConsole consoleMock = Mockito.mock(IConsole.class);
        Mockito.when(consoleMock.readInt()).thenReturn(15);

        //act
        libraryMenu.ShowMenuAndHandleOptionSelection();

        //assert
        Mockito.verify(printerMock, never()).printLn(ArgumentMatchers.eq("Please select a valid option!"));

    }
}
