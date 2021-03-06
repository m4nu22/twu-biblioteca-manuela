package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class LibraryTest {

    private Library library;
    private User user;

    @Before
    public void initialize() {
        user = new User("123-4567", "myPassword", "Manu", "manu@gmail.com", "977383474", UserRole.CUSTOMER);
        library = Library.getInstance();
        library.setCustomerItemMap(new HashMap<String, String>());
    }

    //Book tests
    @Test
    public void getAvailableBooks_returnsEmptyList() {
        //arrange
        library.setItems(new ArrayList<>());

        //act
        List<LibraryItem> books = library.getAvailableItemsPerType(LibraryItemType.BOOK);

        //assert
        assertThat(books.isEmpty(), is(true));
    }

    @Test
    public void getAvailableBooks_returnsBookList() {
        //arrange
        List<LibraryItem> libraryItems = getDefaultMixedList();
        library.setItems(libraryItems);
        List<LibraryItem> expectedBooks = libraryItems.stream().filter(i -> i.getType().equals(LibraryItemType.BOOK)).collect(Collectors.toList());

        //act
        List<LibraryItem> books = library.getAvailableItemsPerType(LibraryItemType.BOOK);

        //assert
        assertThat(expectedBooks, is(books));
    }

    @Test
    public void getAvailableBooks_returnsOnlyAvailableBookList() {
        //arrange
        List<LibraryItem> itemList = getDefaultMixedList();
        itemList.get(1).setCheckedOut(true);
        library.setItems(itemList);
        List<LibraryItem> expectedItemList = itemList.stream().filter(b -> b.getType().equals(LibraryItemType.BOOK)).collect(Collectors.toList());

        //act
        List<LibraryItem> availableBooks = library.getAvailableItemsPerType(LibraryItemType.BOOK);

        //assert
        assertThat(availableBooks.size() == 1, is(true));
        assertThat(availableBooks.get(0).isCheckedOut(), is(false));
        expectedItemList.removeAll(availableBooks);
        assertThat(expectedItemList.stream().allMatch(b -> b.isCheckedOut()), is(true));
    }

    @Test
    public void checkoutBook_emptyStringTitle_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("", LibraryItemType.BOOK, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(false));
        assertThat(library.getCustomerItemMap().size(), is(0));
    }

    @Test
    public void checkoutBook_nullTitle_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem(null, LibraryItemType.BOOK, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(false));
        assertThat(library.getCustomerItemMap().size(), is(0));
    }

    @Test
    public void checkoutBook_notAvailable_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        expectedBookList.get(1).setCheckedOut(true);
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("Narnia", LibraryItemType.BOOK, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutBook_available_returnsTrue() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("Narnia", LibraryItemType.BOOK, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(true));
        assertThat(library.getCustomerItemMap().containsKey("Narnia"), is(true));
        assertThat(library.getCustomerItemMap().get("Narnia"), is("123-4567"));
    }

    @Test
    public void checkoutBook_notFound_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("Divergent", LibraryItemType.BOOK, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(false));
        assertThat(library.getCustomerItemMap().size(), is(0));
    }

    @Test
    public void returnBook_emptyStringTitle_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("", user.getLibraryNumber());

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void returnBook_nullTitle_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook(null, user.getLibraryNumber());

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void returnBook_checkedOutBookIRent_returnsTrue() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.getCustomerItemMap().put("Harry Potter", "123-4567");
        books.get(0).setCheckedOut(true);
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter", user.getLibraryNumber());

        //assert
        assertThat(canReturnBook, is(true));
        assertThat(library.getCustomerItemMap().size(), is(0));
    }

    @Test
    public void returnBook_checkedOutBookIDidntRent_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.getCustomerItemMap().put("Harry Potter", "123-4567");
        books.get(0).setCheckedOut(true);
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter", "234-5678");

        //assert
        assertThat(canReturnBook, is(false));
        assertThat(library.getCustomerItemMap().size(), is(1));
    }

    @Test
    public void returnBook_notCheckedOut_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter", user.getLibraryNumber());

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void returnBook_misspelled_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Pottet", user.getLibraryNumber());

        //assert
        assertThat(canReturnBook, is(false));
    }


    //Movie Tests
    @Test
    public void getAvailableMovies_returnsEmptyList() {
        //arrange
        library.setItems(new ArrayList<>());

        //act
        List<LibraryItem> movies = library.getAvailableItemsPerType(LibraryItemType.MOVIE);

        //assert
        assertThat(movies.isEmpty(), is(true));
    }

    @Test
    public void getAvailableMovies_returnsMovieList() {
        //arrange
        List<LibraryItem> libraryItemsList = getDefaultMixedList();
        List<LibraryItem> expectedMovies = libraryItemsList.stream().filter(i -> i.getType().equals(LibraryItemType.MOVIE)).collect(Collectors.toList());
        library.setItems(libraryItemsList);

        //act
        List<LibraryItem> books = library.getAvailableItemsPerType(LibraryItemType.MOVIE);

        //assert
        assertThat(expectedMovies, is(books));
    }

    @Test
    public void checkoutMovie_notAvailable_returnsFalse() {
        //arrange
        List<LibraryItem> expectedMovieList = getDefaultMixedList();
        expectedMovieList.get(2).setCheckedOut(true);
        library.setItems(expectedMovieList);

        //act
        boolean canCheckedOut = library.checkoutItem("Memento", LibraryItemType.MOVIE, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutMovie_available_returnsTrue() {
        //arrange
        List<LibraryItem> expectedMovieList = getDefaultMixedList();
        library.setItems(expectedMovieList);

        //act
        boolean canCheckedOut = library.checkoutItem("Contratiempo", LibraryItemType.MOVIE, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(true));
    }

    @Test
    public void checkoutMovie_notFound_returnsFalse() {
        //arrange
        List<LibraryItem> expectedMovieList = getDefaultMixedList();
        library.setItems(expectedMovieList);

        //act
        boolean canCheckedOut = library.checkoutItem("narnia", LibraryItemType.MOVIE, user.getLibraryNumber());

        //assert
        assertThat(canCheckedOut, is(false));
    }

    private List<LibraryItem> getDefaultMixedList() {
        Book harryPotter = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        Movie memento = new Movie(1, "Memento", "2000", "Christopher Nolan", 10);
        Movie contratiempo = new Movie(2, "Contratiempo", "2017", "Oriol Paulo");
        return new ArrayList<>(Arrays.asList(harryPotter, narnia, memento, contratiempo));
    }

}
