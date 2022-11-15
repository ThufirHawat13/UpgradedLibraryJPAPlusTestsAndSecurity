package com.library.services;

import com.library.DAO.BookDAO;
import com.library.models.Book;
import com.library.models.Person;
import com.library.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

    private final BookService bookService;

    @Autowired
    BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookDAO bookDAO;

    @Test
    void findAll() {
        bookService.findAll();

        Mockito.verify(bookRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void findById() {
        int id = 0;
        bookService.findById(id);

        Mockito.verify(bookRepository, Mockito.times(1))
                .findById(id);
    }

    @Test
    void findByOwner() {
        Person person = Mockito.mock(Person.class);
        bookService.findByOwner(person);

        Mockito.verify(bookRepository, Mockito.times(1))
                .findByPerson(person);
    }

    @Test
    void findByName() {
        String name = "n";
        bookService.findByName(name);

        Mockito.verify(bookRepository, Mockito.times(1))
                .findByBookNameContaining(name);
    }

    @Test
    void save() {
        Book book = Mockito.mock(Book.class);
        bookService.save(book);

        Mockito.verify(bookRepository, Mockito.times(1))
                .save(book);
    }

    @Test
    void update() {
        int id = 0;
        Book updBook = Mockito.mock(Book.class);
        bookService.update(id, updBook);

        Mockito.verify(bookRepository, Mockito.times(1))
                .save(updBook);
    }

    @Test
    void delete() {
        int id = 0;
        bookService.delete(id);

        Mockito.verify(bookRepository, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void setHolder() {
        int id = 0;
        Person person = Mockito.mock(Person.class);
        bookService.setHolder(id, person);

        Mockito.verify(bookDAO, Mockito.times(1))
                .setHolder(id, person);

    }

    @Test
    void releaseHolder() {
        int id = 0;
        bookService.releaseHolder(id);

        Mockito.verify(bookDAO, Mockito.times(1))
                .releaseHolder(id);

    }
}