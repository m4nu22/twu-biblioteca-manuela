package com.twu.biblioteca.models;

public abstract class LibraryItem {

    private int id;
    private String name;
    private String year;
    private boolean isCheckedOut;

    public LibraryItem(int id, String name, String year) {
        this.id = id;
        this.name = name;
        this.year = year;
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
}
