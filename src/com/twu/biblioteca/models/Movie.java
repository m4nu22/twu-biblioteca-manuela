package com.twu.biblioteca.models;

import com.twu.biblioteca.enums.LibraryItemType;

public class Movie extends LibraryItem {
    private String director;
    private float rating;

    public Movie(int id, String name, String year, String director) {
        super(id, name, year, LibraryItemType.MOVIE);
        this.director = director;
        this.rating = 0;
    }

    public Movie(int id, String name, String year, String director, float rating) {
        super(id, name, year, LibraryItemType.MOVIE);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {

        String rating = this.getRating() == 0 ? "unrated" : this.getRating() + "";
        return "Name: " + this.getName() +
                " | Director: " + this.getDirector() +
                " | Publication Year: " + this.getYear() +
                " | Rating: " + rating;
    }
}
