package com.twu.biblioteca;

import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.ILibraryMenuOptionHandler;
import com.twu.biblioteca.interfaces.IPrinter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.InputMismatchException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class LibraryMenuTest {
    private LibraryMenu libraryMenu;
    private IPrinter printerMock = Mockito.mock(IPrinter.class);
    private IConsole consoleMock = Mockito.mock(IConsole.class);
    private ILibraryMenuOptionHandler handlerMock = Mockito.mock(ILibraryMenuOptionHandler.class);

    @Before
    public void initialize() {
        libraryMenu = new LibraryMenu(printerMock, consoleMock, handlerMock);
    }

    @Test
    public void printWelcomeMessage() {

        //act
        libraryMenu.printWelcomeMessage();

        //assert
        Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));

    }

    @Test
    public void showMenuAndHandleOptionSelection_invalidOption_printsError() {

        //arrange
        Mockito.when(consoleMock.readInt()).thenThrow(new InputMismatchException()).thenReturn(0);

        //act
            libraryMenu.showMenuAndHandleOptionSelection(UserRole.CUSTOMER);

        //assert
            Mockito.verify(printerMock, times(1)).printLn(ArgumentMatchers.eq("Please select a valid option!"));

    }

    @Test
    public void showMenuAndHandleOptionSelection_validOption_doesntThrowError() {

        //arrange
        Mockito.when(consoleMock.readInt()).thenReturn(1).thenReturn(0);

        //act
        libraryMenu.showMenuAndHandleOptionSelection(UserRole.CUSTOMER);

        //assert
        Mockito.verify(printerMock, never()).printLn(ArgumentMatchers.eq("Please select a valid option!"));

    }
}
