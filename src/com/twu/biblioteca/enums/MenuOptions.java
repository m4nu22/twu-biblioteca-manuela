package com.twu.biblioteca.enums;

public enum MenuOptions {
    QUIT("0 - Quit"),
    LIST_BOOKS("1 - List of books"),
    CHECKOUT_BOOK("2 - Checkout Book"),
    RETURN_BOOK("3 - Return Book"),
    LIST_MOVIES("4 - List of movies"),
    CHECKOUT_MOVIE("5 - Checkout MOVIE");

    private String description;

    MenuOptions(String descriptipion) {
        this.description = descriptipion;
    }

    public String getDescription() {
        return description;
    }

}
