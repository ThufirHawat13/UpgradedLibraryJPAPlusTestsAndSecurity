package com.library.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @Column(name = "person_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int personId;

  @Column(name = "name_surname")
  @NotEmpty(message = "This field shouldn't be empty!")
  @Pattern(regexp = "[A-Z]\\w{1,15} [A-Z]\\w{1,20}", message = "Input correct name and surname!")
  private String nameSurname;

  @Column(name = "age_of_birth")
  @NotEmpty(message = "This field shouldn't be empty!")
  @Min(value = 1920, message = "Age should be greater then 1920!")
  @Pattern(regexp = "\\d{4}", message = "Input correct age of birth!")
  @Max(value = 2010, message = "Age shouldn't be greater then 2010!")
  private String ageOfBirth;

  @OneToMany(mappedBy = "person")
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private List<Book> books;

  public Person(String nameSurname, String ageOfBirth) {
    this.nameSurname = nameSurname;
    this.ageOfBirth = ageOfBirth;
  }

  public Person() {
  }

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public String getNameSurname() {
    return nameSurname;
  }

  public void setNameSurname(String nameSurname) {
    this.nameSurname = nameSurname;
  }

  public String getAgeOfBirth() {
    return ageOfBirth;
  }

  public void setAgeOfBirth(String dateOfBirth) {
    this.ageOfBirth = dateOfBirth;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}
