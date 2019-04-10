package com.twu.biblioteca;

import com.twu.biblioteca.enums.LibraryItemType;
import com.twu.biblioteca.interfaces.ILibrary;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library implements ILibrary {

    private List<LibraryItem> libraryItems;
    private Map<String,String> customerItemMap;

    private static Library instance;

    private Library() {
        this.customerItemMap = new HashMap<String,String>();
    }

    public static Library getInstance() {
        if (instance == null)
            instance = new Library();

        return instance;
    }

    public List<LibraryItem> getAvailableItemsPerType(LibraryItemType type) {
        return libraryItems.stream().filter(b -> !b.isCheckedOut() && type.equals(b.getType())).collect(Collectors.toList());
    }

    public boolean checkoutItem(String title, LibraryItemType type, User user){
        boolean canCheckout = false;

        LibraryItem item = libraryItems.stream().filter(b -> b.getType().equals(type) &&
                                                             b.getName().equalsIgnoreCase(title) &&
                                                             !b.isCheckedOut()).findFirst().orElse(null);

        if (item != null) {
            canCheckout = true;
            item.setCheckedOut(true);
            customerItemMap.put(item.getName(),user.getLibraryNumber());

        }

        return canCheckout;
    }

    public boolean returnBook(String title, User user) {
        boolean canReturn = false;

        Book book = (Book) libraryItems.stream().filter(b -> LibraryItemType.BOOK.equals(b.getType()) &&
                                                             (b.getName()).equalsIgnoreCase(title) &&
                                                             b.isCheckedOut()).findFirst().orElse(null);

        if (book != null) {
            canReturn = true;
            book.setCheckedOut(false);
            customerItemMap.remove(book.getName());
        }

        return canReturn;
    }

    public Map<String, String> getCustomerItemMap() {
        return customerItemMap;
    }

    public void setItems(List<LibraryItem> items) {
        this.libraryItems = items;
    }
}
