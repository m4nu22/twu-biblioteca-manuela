package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.interfaces.ILibrary;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class Library implements ILibrary {

    private List<LibraryItem> libraryItems;

    private static Library instance;

    private Library() {}

    public static Library getInstance() {
        if (instance == null)
            instance = new Library();

        return instance;
    }

    public List<LibraryItem> getAvailableItemsPerType(LibraryItemType type) {
        return libraryItems.stream().filter(b -> !b.isCheckedOut() && type.equals(b.getType())).collect(Collectors.toList());
    }

    public boolean checkoutBook(String title) {
        boolean canCheckout = false;

        Book book = (Book) libraryItems.stream().filter(b -> LibraryItemType.book.equals(b.getType()) && title.equalsIgnoreCase(b.getName()) && !b.isCheckedOut()).findFirst().orElse(null);

        if (book != null) {
            canCheckout = true;
            book.setCheckedOut(true);
        }

        return canCheckout;
    }

    public boolean returnBook(String title) {
        boolean canReturn = false;

        Book book = (Book) libraryItems.stream().filter(b -> LibraryItemType.book.equals(b.getType()) && title.equalsIgnoreCase(b.getName()) && b.isCheckedOut()).findFirst().orElse(null);

        if (book != null) {
            canReturn = true;
            book.setCheckedOut(false);
        }

        return canReturn;
    }

    @Override
    public void setItems(List<LibraryItem> items) {
        this.libraryItems = items;
    }
}
