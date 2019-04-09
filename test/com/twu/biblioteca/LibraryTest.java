package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
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
    public void initialize(){
        library = Library.getInstance();
    }

    @Test
    public void getAvailableBooks_returnsEmptyList() {
        //arrange
        library.setBooks(new ArrayList<>());

        //act
        List<Book> books = library.getAvailableBooks();

        //assert
        assertThat(books.isEmpty(), is(true));
    }

    @Test
    public void getAvailableBooks_returnsBookList(){
        //arrange
        List<Book> expectedBookList = getDefaultBookList();
        library.setBooks(expectedBookList);

        //act
        List<Book> books = library.getAvailableBooks();

        //assert
        assertThat(expectedBookList, is(books));
    }

    @Test
    public void getAvailableBooks_returnsOnlyAvailableBookList(){
        //arrange
        List<Book> bookList = getDefaultBookList();
        bookList.get(1).setCheckedOut(true);
        library.setBooks(bookList);

        //act
        List<Book> books = library.getAvailableBooks();

        //assert
        assertThat(books.size() == 1, is(true));
        assertThat(books.get(0).isCheckedOut(), is(false));
        bookList.removeAll(books);
        assertThat(bookList.stream().allMatch(b -> b.isCheckedOut()), is(true));
    }


    @Test
    public void checkoutBook_notAvailable_returnsFalse(){
        //arrange
        List<Book> expectedBookList = getDefaultBookList();
        expectedBookList.get(1).setCheckedOut(true);
        library.setBooks(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutBook("Narnia");

        //assert
        assertThat(canCheckedOut,is(false));
    }

    @Test
    public void checkoutBook_available_returnsTrue(){
        //arrange
        List<Book> expectedBookList = getDefaultBookList();
        library.setBooks(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutBook("Narnia");

        //assert
        assertThat(canCheckedOut,is(true));
    }

    @Test
    public void checkoutBook_notFound_returnsFalse(){
        //arrange
        List<Book> expectedBookList = getDefaultBookList();
        library.setBooks(expectedBookList);

        //act
        boolean canCheckedOut = library.checkoutBook("Divergent");

        //assert
        assertThat(canCheckedOut,is(false));
    }

    @Test
    public void ReturnBook_checkedOut_returnsTrue(){
        //arrange
        List<Book> books = getDefaultBookList();
        books.get(0).setCheckedOut(true);
        library.setBooks(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter");

        //assert
        assertThat(canReturnBook,is(true));
    }

    @Test
    public void ReturnBook_notCheckedOut_returnsFalse(){
        //arrange
        List<Book> books = getDefaultBookList();
        library.setBooks(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Potter");

        //assert
        assertThat(canReturnBook,is(false));
    }

    @Test
    public void ReturnBook_misspelled_returnsFalse(){
        //arrange
        List<Book> books = getDefaultBookList();
        library.setBooks(books);

        //act
        boolean canReturnBook = library.returnBook("Harry Pottet");

        //assert
        assertThat(canReturnBook,is(false));
    }

    public List<Book> getDefaultBookList(){
        Book harryPotter = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        return new ArrayList<>(Arrays.asList(harryPotter,narnia));
    }

}
