package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.models.LibraryItem;

import java.util.List;
import java.util.Map;

public interface ILibrary {

    static Library getInstance() {
        return null;
    }

    List<LibraryItem> getAvailableItemsPerType(LibraryItemType type);

    boolean checkoutItem(String title, LibraryItemType type, String user);

    boolean returnBook(String title, String user);

    void setItems(List<LibraryItem> items);

    Map<String, String> getCustomerItemMap();
}
