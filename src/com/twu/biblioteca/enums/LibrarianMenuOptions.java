package com.twu.biblioteca.enums;

public enum LibrarianMenuOptions {
    QUIT("0 - Quit"),
    LIST_CHECKOUT_BOOKS("1 - List checkout books");

    private String description;

    LibrarianMenuOptions(String descriptipion) {
        this.description = descriptipion;
    }

    public String getDescription() {
        return description;
    }

}
