package com.twu.biblioteca.models;

public class Movie extends LibraryItem {
    private String director;
    private float rating;

    public Movie(int id, String name, String year, String director) {
        super(id, name, year);
        this.director = director;
        this.rating = 0;
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
        return "Name: " + this.getName() +
                " | Director: " + this.getDirector() +
                " | Publication Year: " + this.getYear() +
                " | Rating: " + this.getRating();
    }
}
