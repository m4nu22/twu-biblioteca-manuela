package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;

import java.util.List;

public interface ILibrary {

    static Library getInstance() {
        return null;
    }

    List<LibraryItem> getAvailableItemsPerType(LibraryItemType type);

    boolean checkoutBook(String title);

    boolean returnBook(String title);

    void setItems(List<LibraryItem> items);
}
