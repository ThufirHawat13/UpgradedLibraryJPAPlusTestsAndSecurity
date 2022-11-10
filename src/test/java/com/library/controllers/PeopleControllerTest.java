package com.library.controllers;

import com.library.models.Person;
import com.library.services.BookService;
import com.library.services.PeopleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class PeopleControllerTest {

    private final PeopleController peopleController;

    @Autowired
    PeopleControllerTest(PeopleController peopleController) {
        this.peopleController = peopleController;
    }

    @MockBean
    private PeopleService peopleService;

    @MockBean
    private BookService bookService;

    @Mock
    private Person person;
    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    private int id = 0;

    @Test
    void index() {
        peopleController.index(model);

        Mockito.verify(peopleService, Mockito.times(1)).findAll();
    }

    @Test
    void show() {
        Mockito.when(peopleService.findById(id)).thenReturn(person);
        peopleController.show(id, model);

        Mockito.verify(peopleService, Mockito.times(1)).findById(id);
    }

    @Test
    void create() {
        peopleController.create(model, person, bindingResult);

        Mockito.verify(peopleService, Mockito.times(1)).save(person);
    }

    @Test
    void update() {
        peopleController.update(id, model, person, bindingResult);

        Mockito.verify(peopleService, Mockito.times(1)).update(id, person);
    }

    @Test
    void delete() {
        peopleController.delete(id);

        Mockito.verify(peopleService, Mockito.times(1)).delete(id);
    }

    @Test
    void searchPersonFromName() {
        String name = "n";
        peopleController.searchPersonFromName(name, model);

        Mockito.verify(peopleService, Mockito.times(1)).findByNameContains(name);
    }
}