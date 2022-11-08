package com.library.services;

import com.library.models.Person;
import com.library.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    public Person findByNameSurname(String name) {
       Optional<Person> person = peopleRepository.findByNameSurname(name);
       return person.orElse(null);
    }

    public List<Person> findByNameContains(String contains) {
        return peopleRepository.findByNameSurnameContaining(contains);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updPerson) {
        updPerson.setPersonId(id);
        peopleRepository.save(updPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }






}
