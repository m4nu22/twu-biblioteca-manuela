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
        List<LibraryItem> books = library.getAvailableItemsPerType(LibraryItemType.book);

        //assert
        assertThat(books.isEmpty(), is(true));
    }

    @Test
    public void getAvailableBooks_returnsBookList() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultBookList();
        library.setItems(expectedBookList);

        //act
        List<LibraryItem> books = library.getAvailableItemsPerType(LibraryItemType.book);

        //assert
        assertThat(expectedBookList, is(books));
    }

    @Test
    public void getAvailableBooks_returnsOnlyAvailableBookList() {
        //arrange
        List<LibraryItem> bookList = getDefaultBookList();
        bookList.get(1).setCheckedOut(true);
        library.setItems(bookList);

        //act
        List<LibraryItem> books = library.getAvailableItemsPerType(LibraryItemType.book);

        //assert
        assertThat(books.size() == 1, is(true));
        assertThat(books.get(0).isCheckedOut(), is(false));
        bookList.removeAll(books);
        assertThat(bookList.stream().allMatch(b -> b.isCheckedOut()), is(true));
    }

    @Test
    public void checkoutBook_notAvailable_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultBookList();
        expectedBookList.get(1).setCheckedOut(true);
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutBook("Narnia");

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void checkoutBook_available_returnsTrue() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultBookList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutBook("Narnia");

        //assert
        assertThat(canCheckedOut, is(true));
    }

    @Test
    public void checkoutBook_notFound_returnsFalse() {
        //arrange
        List<LibraryItem> expectedBookList = getDefaultBookList();
        library.setItems(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutBook("Divergent");

        //assert
        assertThat(canCheckedOut, is(false));
    }

    @Test
    public void ReturnBook_checkedOut_returnsTrue() {
        //arrange
        List<LibraryItem> books = getDefaultBookList();
        books.get(0).setCheckedOut(true);
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter");

        //assert
        assertThat(canReturnBook, is(true));
    }

    @Test
    public void ReturnBook_notCheckedOut_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultBookList();
        library.setItems(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter");

        //assert
        assertThat(canReturnBook, is(false));
    }

    @Test
    public void ReturnBook_misspelled_returnsFalse() {
        //arrange
        List<LibraryItem> books = getDefaultBookList();
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
        List<LibraryItem> movies = library.getAvailableItemsPerType(LibraryItemType.movie);

        //assert
        assertThat(movies.isEmpty(), is(true));
    }

    @Test
    public void getAvailableMovies_returnsMovieList(){

    }

    public List<LibraryItem> getDefaultBookList() {
        Book harryPotter = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(harryPotter, narnia));
    }

}
