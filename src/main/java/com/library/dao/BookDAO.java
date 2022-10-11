package com.library.dao;

import com.library.models.Book;
import com.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {

        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Person showPerson(int id) {

        return jdbcTemplate.query("SELECT person.person_id, person.name_surname, person.age_of_birth " +
                        "FROM person INNER JOIN book ON " +
                "person.person_id = book.person_id WHERE book.person_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Book show(int id) {

        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);

    }

    public void save(Book book) {

        jdbcTemplate.update("INSERT INTO book(book_name, year_of_writing, author) " +
                        "VALUES(?, ?, ?)",
                book.getBook_name(), Integer.parseInt(book.getYear_of_writing()), book.getAuthor());
    }

    public void update(int id, Book book) {

        jdbcTemplate.update("UPDATE book SET book_name=?, year_of_writing=?, author=? " +
                "WHERE book_id=?",
                book.getBook_name(), Integer.parseInt(book.getYear_of_writing()), book.getAuthor(), id);
    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);

    }

    public Person showHolder(int id) {

        return jdbcTemplate.query("SELECT person.person_id, person.name_surname, person.age_of_birth FROM person " +
                        "INNER JOIN book ON person.person_id = book.person_id WHERE book.book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void setHolder(Person person, int id) {

        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person.getPerson_id(), id);
    }

    public void releaseHolder(int id) {

        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?", id);
    }
}
