package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class LibrarianMenuOptionHandlerTest {
    private LibrarianMenuOptionHandler librarianMenuOptionHandler;
    private IPrinter printerMock = Mockito.mock(IPrinter.class);
    private Library libraryMock = Mockito.mock(Library.class);

    @Before
    public void initialize() {
        librarianMenuOptionHandler = new LibrarianMenuOptionHandler(printerMock, libraryMock);
        Mockito.when(libraryMock.getAvailableItemsPerType(LibraryItemType.BOOK)).thenReturn(addBooksToLibrary());
    }

    @Test
    public void handleMenuOptionSelected_validBooksOption_printsBooks() {
        //act
        librarianMenuOptionHandler.handleMenuOptionSelected(1);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Library Item Name | User ID Number"));
    }

    @Test
    public void handleMenuOptionSelected_invalidOption_printsErrorMessage() {
        //act
        librarianMenuOptionHandler.handleMenuOptionSelected(10);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Please select a valid option!"));
    }

    @Test
    public void handleMenuOptionSelected_quitOptionSelected_printSeeYou() {
        //act
        librarianMenuOptionHandler.handleMenuOptionSelected(0);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("See you!"));
    }

    @Test
    public void handleMenuOptionSelected_listCheckedOutBooksOptionSelected_printsBookUserList() {
        //arrange
        Map<String, String> customerItemMap = new HashMap<>();
        customerItemMap.put("narnia", "123-764");
        Mockito.when(libraryMock.getCustomerItemMap()).thenReturn(customerItemMap);

        //act
        librarianMenuOptionHandler.handleMenuOptionSelected(1);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq(customerItemMap));
    }

    private ArrayList<LibraryItem> addBooksToLibrary() {
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(hp, narnia));
    }
}
