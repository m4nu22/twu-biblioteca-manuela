package com.twu.biblioteca.models;

import com.twu.biblioteca.enums.LibraryItemType;

public abstract class LibraryItem {

    private int id;
    private String name;
    private String year;
    private boolean isCheckedOut;
    private LibraryItemType type;

    public LibraryItem(int id, String name, String year, LibraryItemType type) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.type = type;
        isCheckedOut = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public LibraryItemType getType() {
        return type;
    }

    public void setType(LibraryItemType type) {
        this.type = type;
    }
}
