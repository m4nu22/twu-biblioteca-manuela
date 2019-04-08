package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class LibraryTest {

    private Library library;

    @Test
    public void getBooks_returnsEmptyList() {
        //arrange
        library = Library.getInstance();
        library.init(new ArrayList<>());

        //act
        List<Book> books = library.getBooks();

        //assert
        assertThat(books.isEmpty(), is(true));
    }

    @Test
    public void getBooks_returnsBookList(){
        //arrange
        Book harryPotter = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        List<Book> expectedBookList = new ArrayList<>(Arrays.asList(harryPotter,narnia));
        library = Library.getInstance();
        library.init(expectedBookList);

        //act
        List<Book> books = library.getBooks();

        //assert
        assertThat(expectedBookList, is(books));

    }
}
