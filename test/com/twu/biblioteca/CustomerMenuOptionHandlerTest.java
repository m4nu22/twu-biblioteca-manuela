package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class CustomerMenuOptionHandlerTest {

    private CustomerMenuOptionHandler customerMenuOptionHandler;
    private IPrinter printerMock = Mockito.mock(IPrinter.class);
    private IConsole consoleMock = Mockito.mock(IConsole.class);
    private Library libraryMock = Mockito.mock(Library.class);
    private User user;

    @Before
    public void initialize() {
        user = new User("123-4567", "myPassword", "Manu", "manu@gmail.com", "977383474", UserRole.CUSTOMER);
        customerMenuOptionHandler = new CustomerMenuOptionHandler(printerMock, consoleMock, libraryMock, user);
        Mockito.when(libraryMock.getAvailableItemsPerType(LibraryItemType.BOOK)).thenReturn(addBooksToLibrary());
        Mockito.when(libraryMock.getAvailableItemsPerType(LibraryItemType.MOVIE)).thenReturn(addMoviesToLibrary());
    }

    @Test
    public void handleMenuOptionSelected_validBooksOption_printsBooks() {
        //act
        customerMenuOptionHandler.handleMenuOptionSelected(1);

        //assert
        Mockito.verify(printerMock, times(1)).printList(anyList());
    }

    @Test
    public void handleMenuOptionSelected_validMoviesOption_printsMovies() {
        //act
        customerMenuOptionHandler.handleMenuOptionSelected(4);

        //assert
        Mockito.verify(printerMock, times(1)).printList(anyList());
    }

    @Test
    public void handleMenuOptionSelected_invalidOption_printsErrorMessage() {
        //act
        customerMenuOptionHandler.handleMenuOptionSelected(10);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Please select a valid option!"));
    }

    @Test
    public void handleMenuOptionSelected_quitOptionSelected_printSeeYou() {
        //act
        customerMenuOptionHandler.handleMenuOptionSelected(0);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("See you!"));
    }

    @Test
    public void handleMenuOptionSelected_checkoutAvailableBook_printsSuccessMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Narnia");
        Mockito.when(libraryMock.checkoutItem(anyString(), eq(LibraryItemType.BOOK), eq(user.getLibraryNumber()))).thenReturn(true);

        //act
        customerMenuOptionHandler.handleMenuOptionSelected(2);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Thank you! Enjoy the book!"));
    }

    @Test
    public void handleMenuOptionSelected_checkoutAvailableMovie_printsSuccessMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Memento");
        Mockito.when(libraryMock.checkoutItem(anyString(), eq(LibraryItemType.MOVIE), eq(user.getLibraryNumber()))).thenReturn(true);

        //act
        customerMenuOptionHandler.handleMenuOptionSelected(5);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Thank you! Enjoy the movie!"));
    }

    @Test
    public void handleMenuOptionSelected_checkoutNotAvailableBook_printsErrorMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Divergent");
        Mockito.when(libraryMock.checkoutItem(anyString(), eq(LibraryItemType.BOOK), eq(user.getLibraryNumber()))).thenReturn(false);

        //act
        customerMenuOptionHandler.handleMenuOptionSelected(2);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Sorry, that book is not available"));
    }

    @Test
    public void handleMenuOptionSelected_checkoutNotAvailableMovie_printsErrorMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Toy Story 3");
        Mockito.when(libraryMock.checkoutItem(anyString(), eq(LibraryItemType.MOVIE), eq(user.getLibraryNumber()))).thenReturn(false);

        //act
        customerMenuOptionHandler.handleMenuOptionSelected(5);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Sorry, that movie is not available"));
    }

    @Test
    public void handleMenuOptionSelected_returnCheckedOutBook_printsSuccessMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Narnia");
        Mockito.when(libraryMock.returnBook(anyString(), eq(user.getLibraryNumber()))).thenReturn(true);

        //act
        customerMenuOptionHandler.handleMenuOptionSelected(3);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Thank you for returning the book"));
    }

    @Test
    public void handleMenuOptionSelected_returnNotCheckedOutBook_printsErrorMsg() {
        //arrange
        Mockito.when(consoleMock.readString()).thenReturn("Divergent");
        Mockito.when(libraryMock.returnBook(anyString(), eq(user.getLibraryNumber()))).thenReturn(false);

        //act
        customerMenuOptionHandler.handleMenuOptionSelected(3);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("That is not a valid book to return"));
    }

    @Test
    public void handleMenuOptionSelected_printMyInfoOptionSelect_printsCustomerData() {
        customerMenuOptionHandler.setUser(new User("123-4567", "myPassword", "Manu", "manu@gmail.com", "977383474", UserRole.CUSTOMER));
        //act
        customerMenuOptionHandler.handleMenuOptionSelected(6);

        //assert
        Mockito.verify(printerMock, times(1)).printLn(eq("Name: Manu | Email: manu@gmail.com | Phone: 977383474"));
    }

    private ArrayList<LibraryItem> addBooksToLibrary() {
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(hp, narnia));
    }

    private ArrayList<LibraryItem> addMoviesToLibrary() {
        Movie memento = new Movie(1, "Memento", "2000", "Christopher Nolan", 10);
        Movie contratiempo = new Movie(2, "Contratiempo", "2017", "Oriol Paulo");
        Movie aStarIsBorn = new Movie(2, "A star is born", "2018", "Bradley Cooper", 8);
        return new ArrayList<>(Arrays.asList(memento, contratiempo, aStarIsBorn));
    }
}
