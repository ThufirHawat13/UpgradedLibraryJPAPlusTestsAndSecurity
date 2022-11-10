package com.library.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;


@Entity
@Table(name = "book")
public class Book {


    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "book_name")
    @NotNull(message = "This field shouldn't be empty!")
    @Pattern(regexp = "[A-Z\\d].*{1,100}", message = "Input correct book name!")
    private String bookName;

    @Column(name = "year_of_writing")
    @NotNull(message = "This field shouldn't be empty")
    @Min(value = 500, message = "Year of writing should be greater then 500!")
    private int yearOfWriting;

    @Column(name = "author")
    @NotNull(message = "This field shouldn't be empty!")
    @Pattern(regexp = "[A-Z]\\w+\\s[A-Z][.][A-Z][.]", message = "Input correct author!")
    @Size(min = 1, max = 30, message = "Author's name shouldn't be between 2 and 30 characters!")
    private String author;

    @ManyToOne
    @JoinColumn(name = "person_id",
            referencedColumnName = "person_id")
    private Person person;


    public Book(String bookName, int yearOfWriting, String author) {
        this.bookName = bookName;
        this.yearOfWriting = yearOfWriting;
        this.author = author;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
