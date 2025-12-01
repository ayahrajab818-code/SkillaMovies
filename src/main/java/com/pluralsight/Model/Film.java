package com.pluralsight.Model;

public class Film {

    private int id;
    private String title;
    private int length;
    private String rating;

    public Film(int id, String title, int length, String rating) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", rating='" + rating + '\'' +
                '}';
    }
}
