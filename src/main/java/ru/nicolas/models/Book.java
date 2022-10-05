package ru.nicolas.models;

import javax.validation.constraints.Pattern;

public class Book {
    private int id;
    private int client_id;

  // @Pattern(regexp = "\\d{4}", message = "Year should contain 4 digits!")
    private int year;


    private String title;
    private String author;



    public Book() {

    }

    public Book(int id, int client_id, int year, String title, String author) {
        this.id = id;
        this.client_id = client_id;
        this.year = year;
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
