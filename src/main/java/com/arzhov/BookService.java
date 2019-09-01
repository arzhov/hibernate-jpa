package com.arzhov;

import com.arzhov.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BookService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManager em;
  private EntityTransaction tx;

  // ======================================
  // =            Constructors            =
  // ======================================

  public BookService(EntityManager em) {
    this.em = em;
    this.tx = em.getTransaction();
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  public Book createBook(Book book) {
    tx.begin();
    em.persist(book);
    tx.commit();
    return book;
  }

  public Book createBook(Long id, String title, String description, Float unitCost, String isbn, Integer nbOfPage) {
    Book book = new Book();
    book.setId(id);
    book.setTitle(title);
    book.setDescription(description);
    book.setUnitCost(unitCost);
    book.setIsbn(isbn);
    book.setNbOfPage(nbOfPage);

    tx.begin();
    em.persist(book);
    tx.commit();

    return book;
  }

  public void removeBook(Book book) {
    tx.begin();
    em.remove(em.merge(book));
    tx.commit();
  }

  public void removeBook(Long id) {
    Book book = em.find(Book.class, id);
    if (book != null) {
      tx.begin();
      em.remove(book);
      tx.commit();
    }
  }

  public Book findBook(Long id) {
    return em.find(Book.class, id);
  }

  public Book raiseUnitCost(Long id, Float raise) {
    Book book = em.find(Book.class, id);
    if (book != null) {
      tx.begin();
      book.setUnitCost(book.getUnitCost() + raise);
      tx.commit();
    }
    return book;
  }
}