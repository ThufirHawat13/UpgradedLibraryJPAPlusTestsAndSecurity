package com.library.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "name_surname")
    @NotEmpty(message = "This field shouldn't be empty!" )
    @Pattern(regexp = "[A-Z]\\w{1,15} [A-Z]\\w{1,20}", message = "Input correct name and surname!")
    private String nameSurname;

    @Column(name = "age_of_birth")
    @NotEmpty(message = "This field shouldn't be empty!")
    @Pattern(regexp = "\\d{4}", message = "Input correct age of birth!")
    @Min(value = 1920, message = "Age should be greater then 1920!")
    private String dateOfBirth;

    @OneToMany(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Book book;

    public Person(String nameSurname, String dateOfBirth) {
        this.nameSurname = nameSurname;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    public int getPerson_id() { return personId; }


    public void setPerson_id(int person_id) {
        this.personId = person_id;
    }

    public String getName_surname() {
        return nameSurname;
    }

    public void setName_surname(String name_surname) {
        this.nameSurname = name_surname;
    }

    public String getAge_of_birth() {
        return dateOfBirth;
    }

    public void setAge_of_birth(String age_of_birth) {
        this.dateOfBirth = age_of_birth;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
