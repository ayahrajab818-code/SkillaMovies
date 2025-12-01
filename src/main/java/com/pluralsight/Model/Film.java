package com.pluralsight.Model;

public class Film {

    private int id;
    private String title;
    private int length;
    private String description;
    private int year;

    public Film(int id, String title, int length, String description, int year) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.description = description;
        this.year = year;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return id + " - " + title + " (" + year + ")";
    }
}
