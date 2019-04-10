package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class LibraryTest {

    private Library library;

    @Before
    public void initialize() {
        library = Library.getInstance();
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
        boolean canCheckedOut = library.checkoutItem("",LibraryItemType.BOOK);

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutBook_nullTitle_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem(null,LibraryItemType.BOOK);

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutBook_notAvailable_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        expectedBookList.get(1).setCheckedOut(true);
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("Narnia",LibraryItemType.BOOK);

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutBook_available_returnsTrue() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("Narnia",LibraryItemType.BOOK);

        //assert
        assertThat(canCheckedOut, is(true));
    }

    @Test
    public void checkoutBook_notFound_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultMixedList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutItem("Divergent",LibraryItemType.BOOK);

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void returnBook_emptyStringTitle_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("");

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void returnBook_nullTitle_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook(null);

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void returnBook_checkedOut_returnsTrue() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        books.get(0).setCheckedOut(true);
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter");

        //assert
        assertThat(canReturnBook, is(true));
    }

    @Test
    public void returnBook_notCheckedOut_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter");

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void returnBook_misspelled_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultMixedList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Pottet");

        //assert
        assertThat(canReturnBook, is(false));
    }


    //Movie Tests
    @Test
    public void getAvailableMovies_returnsEmptyList(){
        //arrange
        library.setItems(new ArrayList<>());

        //act
        List<LibraryItem> movies = library.getAvailableItemsPerType(LibraryItemType.MOVIE);

        //assert
        assertThat(movies.isEmpty(), is(true));
    }

    @Test
    public void getAvailableMovies_returnsMovieList(){
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
        boolean canCheckedOut = library.checkoutItem("Memento",LibraryItemType.MOVIE);

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutMovie_available_returnsTrue() {
        //arrange
        List<LibraryItem> expectedMovieList = getDefaultMixedList();
        library.setItems(expectedMovieList);

        //act
        boolean canCheckedOut = library.checkoutItem("Contratiempo",LibraryItemType.MOVIE);

        //assert
        assertThat(canCheckedOut, is(true));
    }

    @Test
    public void checkoutMovie_notFound_returnsFalse() {
        //arrange
        List<LibraryItem> expectedMovieList = getDefaultMixedList();
        library.setItems(expectedMovieList);

        //act
        boolean canCheckedOut = library.checkoutItem("narnia", LibraryItemType.MOVIE);

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
