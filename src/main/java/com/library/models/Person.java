package com.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Person {

    private int person_id;

    @NotEmpty(message = "This field shouldn't be empty!" )
    @Pattern(regexp = "[A-Z]\\w{1,15} [A-Z]\\w{1,20}", message = "Input correct name and surname!")
    private String name_surname;

    @NotEmpty(message = "This field shouldn't be empty!")
    @Pattern(regexp = "\\d{4}", message = "Input correct age of birth!")
    @Min(value = 1920, message = "Age should be greater then 1920!")
    private String age_of_birth;

    public Person(int person_id, String name_surname, String date_of_birth) {
        this.person_id = person_id;
        this.name_surname = name_surname;
        this.age_of_birth = date_of_birth;
    }

    public Person() {
    }

    public int getPerson_id() { return person_id; }


    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName_surname() {
        return name_surname;
    }

    public void setName_surname(String name_surname) {
        this.name_surname = name_surname;
    }

    public String getAge_of_birth() {
        return age_of_birth;
    }

    public void setAge_of_birth(String age_of_birth) {
        this.age_of_birth = age_of_birth;
    }
}
