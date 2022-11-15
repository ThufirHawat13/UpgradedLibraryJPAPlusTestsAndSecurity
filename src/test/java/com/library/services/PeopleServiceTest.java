package com.library.services;

import com.library.models.Person;
import com.library.repositories.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PeopleServiceTest {

    private final PeopleService peopleService;

    @Autowired
    PeopleServiceTest(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @MockBean
    private PeopleRepository peopleRepository;

    @Test
    void save() {
        Person person = new Person();
        peopleService.save(person);

        Mockito.verify(peopleRepository, Mockito.times(1))
                .save(person);
    }

    @Test
    void findAll() {
        peopleService.findAll();

        Mockito.verify(peopleRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void findById() {
        int id = 0;
        peopleService.findById(id);

        Mockito.verify(peopleRepository, Mockito.times(1))
                .findById(id);
    }

    @Test
    void findByName() {
        String name = "name";
        peopleService.findByNameContains(name);

        Mockito.verify(peopleRepository, Mockito.times(1))
                .findByNameSurnameContaining(name);
    }

    @Test
    void update() {
        int id = 0;
        Person updPerson = new Person();
        peopleService.update(id, updPerson);

        Mockito.verify(peopleRepository, Mockito.times(1))
                .save(updPerson);
    }

    @Test
    void delete() {
        int id = 0;
        peopleService.delete(id);

        Mockito.verify(peopleRepository, Mockito.times(1))
                .deleteById(id);
    }
}