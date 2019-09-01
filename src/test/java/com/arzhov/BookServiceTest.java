package com.arzhov;

import com.arzhov.entity.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class BookServiceTest {
    private static EntityManagerFactory emf;
    private static BookService bookService;

    @Before
    public void initEntityManager() {
        emf = Persistence.createEntityManagerFactory("Memory");
        bookService = new BookService(emf.createEntityManager());
    }

    @After
    public void closeEntityManager() {
        if (emf != null) emf.close();
    }

    @Test
    public void createBook() {
        bookService.createBook(4044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247);

        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, 4044L);
        em.close();
        assertNotNull(book);
        assertEquals(book.getId().longValue(), 4044L);
        assertEquals(book.getTitle(), "H2G2");
    }

    @Test
    public void testCreateBook() {
        bookService.createBook(new Book(5044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));

        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, 5044L);
        em.close();
        assertNotNull(book);
        assertEquals(book.getId().longValue(), 5044L);
        assertEquals(book.getTitle(), "H2G2");
    }

    @Test
    public void removeBook() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Book(6044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));
        em.getTransaction().commit();
        em.close();

        bookService.removeBook(6044L);

        Book book = emf.createEntityManager().find(Book.class, 6044L);
        assertNull(book);
    }

    @Test
    public void testRemoveBook() {
        bookService.createBook(new Book(7044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));

        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, 7044L);
        em.close();

        bookService.removeBook(book);

        assertNull(emf.createEntityManager().find(Book.class, 7044L));
    }

    @Test
    public void findBook() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Book(8044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));
        em.getTransaction().commit();
        em.close();

        Book book = bookService.findBook(8044L);
        assertNotNull(book);
        assertEquals(book.getId().longValue(), 8044L);
    }

    @Test
    public void raiseUnitCost() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Book(9044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));
        em.getTransaction().commit();
        em.close();

        bookService.raiseUnitCost(9044L, 10.0f);

        em = emf.createEntityManager();
        Book book = em.find(Book.class, 9044L);
        em.close();

        assertEquals(book.getUnitCost(), 22.5f, 0.0001);

    }
}
