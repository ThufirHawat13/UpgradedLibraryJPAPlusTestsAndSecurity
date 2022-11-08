package com.library.repositories;

import com.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    List<Person> findByNameSurnameContaining(String containing);
    Optional<Person> findByNameSurname(String name);

}
