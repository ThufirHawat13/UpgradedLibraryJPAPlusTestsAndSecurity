package com.library.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "book")
public class Book {


    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name = "book_name")
    @NotNull(message = "This field shouldn't be empty!")
    @Pattern(regexp = "[A-Z\\d].*{1,100}", message = "Input correct book name!")
    private String book_name;

    @Column(name = "year_of_writing")
    @NotNull(message = "This field shouldn't be empty")
    @Pattern(regexp  = "\\d{3,4}", message = "Input correct year of writing!")
    @Min(value = 500, message = "Year of writing should be greater then 500!")
    private String year_of_writing;

    @Column(name = "author")
    @NotNull(message = "This field shouldn't be empty!")
    @Pattern(regexp = "[A-Z]\\w+\\s[A-Z][.][A-Z][.]", message = "Input correct author!")
    @Size(min = 1, max = 30, message = "Author's name shouldn't be between 2 and 30 characters!")
    private String author;

    @ManyToOne
    @JoinColumn(name = "person_id",
            referencedColumnName = "person_id")
    private Person person;


    public Book(String book_name, String year_of_writing, String author) {
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


    public String getYear_of_writing() {
        return year_of_writing;
    }

    public void setYear_of_writing(String year_of_writing) {
        this.year_of_writing = year_of_writing;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
