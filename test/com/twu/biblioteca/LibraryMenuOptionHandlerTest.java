package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class LibraryMenuOptionHandlerTest {

    private LibraryMenuOptionHandler libraryMenuOptionHandler;
    private IPrinter printerMock = Mockito.mock(IPrinter.class);
    private IConsole consoleMock = Mockito.mock(IConsole.class);
    private Library libraryMock = Mockito.mock(Library.class);

    @Before
    public void initialize() {
        libraryMenuOptionHandler = new LibraryMenuOptionHandler(printerMock, consoleMock, libraryMock);
        Mockito.when(libraryMock.getAvailableItemsPerType(LibraryItemType.book)).thenReturn(addBooksToLibrary());
    }

    @Test
    public void handleMenuOptionSelected_validBooksOption_printsBooks() {
        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(1);

        //assert
        Mockito.verify(printerMock, times(1)).printList(anyList());

    }

    @Test
    public void handleMenuOptionSelected_invalidOption_printsErrorMessage() {
        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(10);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Please select a valid option!"));

    }

    @Test
    public void handleMenuOptionSelected_quitOptionSelected_printSeeYou() {
        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(0);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("See you!"));

    }

    @Test
    public void handleMenuOptionSelected_checkoutAvailableBook_printsSuccessMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Narnia");
        Mockito.when(libraryMock.checkoutItem(anyString(),eq(LibraryItemType.book))).thenReturn(true);

        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(2);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Thank you! Enjoy the book!"));

    }

    @Test
    public void handleMenuOptionSelected_checkoutNotAvailableBook_printsErrorMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Divergent");
        Mockito.when(libraryMock.checkoutItem(anyString(),eq(LibraryItemType.book))).thenReturn(false);

        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(2);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Sorry, that book is not available"));
    }

    @Test
    public void handleMenuOptionSelected_returnCheckedOutBook_printsSuccessMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Narnia");
        Mockito.when(libraryMock.returnBook(anyString())).thenReturn(true);

        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(3);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Thank you for returning the book"));
    }

    @Test
    public void handleMenuOptionSelected_returnNotCheckedOutBook_printsErrorMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Divergent");
        Mockito.when(libraryMock.returnBook(anyString())).thenReturn(false);

        //act
        libraryMenuOptionHandler.handleMenuOptionSelected(3);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("That is not a valid book to return"));
    }

    private ArrayList<LibraryItem> addBooksToLibrary() {
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(hp, narnia));
    }
}
