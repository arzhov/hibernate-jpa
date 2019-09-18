package com.arzhov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.arzhov.entity.CD;

public class MyTest extends BaseTest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory("MySQL");
    }

    @AfterAll
    public static void closeEntityManager() {
        if (emf != null) emf.close();
    }

    @Test
    public void testLazyInitializationException() {
        final CD cd = generateCD();
        cd.addMusician(generateMusician());
        cd.addMusician(generateMusician());

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
        em.clear();
        em.close();

        em = emf.createEntityManager();
        final CD findCD = em.find(CD.class, cd.getId());
        em.close();

        assertNotNull(findCD);
        assertNotNull(findCD.getId());
        assertThrows(LazyInitializationException.class, () -> {
            findCD.getMusicians().size();
        });
    }

    @Test
    public void testJoinFetch() {
        final CD cd = generateCD();
        cd.addMusician(generateMusician());
        cd.addMusician(generateMusician());


        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        final TypedQuery<CD> q = em.createQuery("from CD c join fetch c.musicians where c.id = :id", CD.class);
        q.setParameter("id", cd.getId());
        final CD findCD = q.getSingleResult();
        em.close();

        assertNotNull(findCD);
        assertNotNull(findCD.getId());
        assertEquals(findCD.getMusicians().size(), 2);
    }

}
