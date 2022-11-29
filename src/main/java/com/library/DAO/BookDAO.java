package com.library.DAO;

import com.library.models.Book;
import com.library.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional(readOnly = true)
public class BookDAO {

  private final EntityManager entityManager;

  @Autowired
  public BookDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public void setHolder(int bookId, Person bookHolder) {
    Session session = entityManager.unwrap(Session.class);
    Book book = session.get(Book.class, bookId);
    book.setPerson(bookHolder);
    session.saveOrUpdate(book);
  }

  @Transactional
  public void releaseHolder(int bookId) {
    Session session = entityManager.unwrap(Session.class);
    Book book = session.get(Book.class, bookId);
    book.getPerson().getBooks().remove(book);
    book.setPerson(null);
    session.saveOrUpdate(book);

  }


}
