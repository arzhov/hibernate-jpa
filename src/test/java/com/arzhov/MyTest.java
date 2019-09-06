package com.arzhov;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.arzhov.entity.Book;
import com.arzhov.entity.Book_;

public class MyTest {
	private static EntityManagerFactory emf;
	private static BookService bookService;

	@Before
	public void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("MySQL");
		bookService = new BookService(emf.createEntityManager());
	}

	@After
	public void closeEntityManager() {
		if (emf != null) emf.close();
	}

	@Test
	public void my() {
		Book book = new Book(100L, "T", "Des", 10.0f, "123456", 100);

		final EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
//        em.clear();

		book = new Book(100L, "T_____", "Des", 10.0f, "123456", 100);

//        final Book mb = em.find(Book.class, book.getId());
		final Book mb = em.merge(book);
//        em.getTransaction().begin();
//        em.persist(mb);
//        em.getTransaction().commit();
		em.flush();

		em.close();
	}

	@Test
	public void myCriteria(){
		bookService.createBook(200L, "T2", "Book 2", 10.0f, "1234-5678-5678", 200);

		final EntityManager em = emf.createEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Book> q = cb.createQuery(Book.class);
		final Root<Book> root = q.from(Book.class);
		q.select(root);
		q.where(cb.like(root.get(Book_.description), "%Book%"));

		final List<Book> resultList = em.createQuery(q).getResultList();

		assertEquals(resultList.size(), 1);
	}
}
