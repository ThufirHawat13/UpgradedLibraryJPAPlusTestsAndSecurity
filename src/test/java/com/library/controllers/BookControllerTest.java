package com.library.controllers;


import com.library.models.Book;
import com.library.models.Person;
import com.library.services.BookService;
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
class BookControllerTest {

    private final BookController bookController;

    @Autowired
    BookControllerTest(BookController bookController, BookService bookService) {
        this.bookController = bookController;
    }

    @MockBean
    private BookService bookService;

    @Mock
    private Model model;

    @Mock
    private Book book;

    @Mock
    private BindingResult bindingResult;

    private int id = 0;



    @Test
    void index() {
        bookController.index(model);

        Mockito.verify(bookService, Mockito.times(1)).findAll();
    }

    @Test
    void show() throws NullPointerException{
        Mockito.when(bookService.findById(id)).thenReturn(book);
        bookController.show(id, model);

        Mockito.verify(bookService, Mockito.times(1))
                .findById(id);
    }

    @Test
    void create() {
        bookController.create(model, book, bindingResult);

        Mockito.verify(bookService, Mockito.times(1))
                .save(book);
    }

    @Test
    void update() {
        bookController.update(id, model, book);

        Mockito.verify(bookService, Mockito.times(1)).update(id, book);
    }

    @Test
    void delete() {
        bookController.delete(id);

        Mockito.verify(bookService, Mockito.times(1)).delete(id);
    }

    @Test
    void setHolder() {
        Person person = Mockito.mock(Person.class);
        bookController.setHolder(id, model, person);

        Mockito.verify(bookService, Mockito.times(1)).setHolder(id, person);
    }

    @Test
    void releaseHolder() {
        bookController.releaseHolder(id);

        Mockito.verify(bookService, Mockito.times(1)).releaseHolder(id);
    }

    @Test
    void searchBook() {
        String bookName = "n";
        bookController.searchBook(bookName, model);

        Mockito.verify(bookService, Mockito.times(1)).findByName(bookName);
    }

}