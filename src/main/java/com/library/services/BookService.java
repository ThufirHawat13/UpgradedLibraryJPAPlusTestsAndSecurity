package com.library.services;

import com.library.DAO.BookDAO;
import com.library.models.Book;
import com.library.models.Person;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    private final BookDAO bookDAO;

    @Autowired
    public BookService(BookRepository bookRepository, BookDAO bookDAO) {
        this.bookRepository = bookRepository;
        this.bookDAO = bookDAO;
    }

    public List<Book> findAll() { return bookRepository.findAll(); }

    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    public List<Book> findByOwner(Person person) {
        return bookRepository.findByPerson(person);
    }

    public List<Book> findByName(String name) {
        return bookRepository.findByBookNameContaining(name);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updBook) {
        updBook.setBookId(id);
        bookRepository.save(updBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setHolder(int id, Person person) {
        bookDAO.setHolder(id, person);
    }

    @Transactional
    public void releaseHolder(int id) {
        bookDAO.releaseHolder(id);
    }












}
