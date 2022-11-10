package com.library.util;

import com.library.models.Person;
import com.library.services.PeopleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.Errors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonValidatorTest {

     private final PersonValidator personValidator;

     @Autowired
     PersonValidatorTest(PersonValidator personValidator) {
        this.personValidator = personValidator;
     }

    @MockBean
    private PeopleService peopleService;

     private static final String nameSurname = "Name Surname";
     private static final Person person = Mockito.mock(Person.class);

     @BeforeAll
     public static void setup() {
         Mockito.when(person.getNameSurname())
                 .thenReturn(nameSurname);
     }

    @Test
    void validateShouldAcceptUserWithNewNameAndSurname() {
        Mockito.when(peopleService.findByNameSurname(nameSurname)).thenReturn(null);
        Errors errors = Mockito.mock(Errors.class);
        personValidator.validate(person, errors);
        Mockito.verify(errors, Mockito.times(0))
                .rejectValue(ArgumentMatchers.any(),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any());
    }

    @Test
    void validateShouldRejectUserWithAAlreadyUsedNameAndSurname() {
         Mockito.when(peopleService.findByNameSurname(nameSurname)).thenReturn(person);
         Errors errors = Mockito.mock(Errors.class);
         personValidator.validate(person, errors);
         Mockito.verify(errors, Mockito.times(1))
                 .rejectValue(ArgumentMatchers.any(),
                         ArgumentMatchers.any(),
                         ArgumentMatchers.any());
    }

}