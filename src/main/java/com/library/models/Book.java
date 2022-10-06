package com.library.models;

public class Book {


    private int book_id;

    private String book_name;

    private int year_of_writing;

    private String author;


    public Book(int book_id, String book_name, int year_of_writing, String author) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.year_of_writing = year_of_writing;
        this.author = author;
    }

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getYear_of_writing() {
        return year_of_writing;
    }

    public void setYear_of_writing(int year_of_writing) {
        this.year_of_writing = year_of_writing;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
