package com.library.dao;

import com.library.models.Book;
import com.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {

        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[]{id}
                , new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person show(String name_surname) {

        return jdbcTemplate.query("SELECT * FROM person WHERE name_surname = ?", new  Object[]{name_surname},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO person(name_surname, age_of_birth) VALUES(?, ?)",
                person.getName_surname(), person.getAge_of_birth());
    }

    public void update(Person person, int id) {

        jdbcTemplate.update("UPDATE person SET name_surname=?, age_of_birth=? WHERE person_id=?",
                person.getName_surname(), person.getAge_of_birth(), id);

    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public List<Book> showBooks(int id) {

        return  jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
