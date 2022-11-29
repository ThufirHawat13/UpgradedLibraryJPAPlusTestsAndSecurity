package com.library.util;


import com.library.models.Person;
import com.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {


  private final PeopleService peopleService;

  @Autowired
  public PersonValidator(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person person = (Person) target;

    if (peopleService.findByNameSurname(person.getNameSurname()) != null) {
      errors.rejectValue("nameSurname", "",
          "This name and surname is already exists in database!");
    }

  }
}
