package com.arzhov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.arzhov.entity.CD;

public class CDServiceTest extends BaseTest {
    private static EntityManagerFactory emf;
    private static CDService cdService;

    @BeforeAll
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory("MySQL");
        cdService = new CDService(emf.createEntityManager());
    }

    @AfterAll
    public static void closeEntityManager() {
        if (emf != null) emf.close();
    }

    @Test
    public void createCD() {
        final CD cd = generateCD();

        cdService.createCD(cd);

        final EntityManager em = emf.createEntityManager();
        final CD findCD = em.find(CD.class, cd.getId());
        em.close();
        assertNotNull(findCD);
        assertNotNull(findCD.getId());
        assertNotNull(findCD.getTitle());
    }

    @Test
    public void testCreateCD() {
        final CD cd =  cdService.createCD(new CD("Selling England by the Pound", "5th studio album by the progressive rock band Genesis", 12.5F, 53.39F, "Progressive Rock"));

        final EntityManager em = emf.createEntityManager();
        final CD findCD = em.find(CD.class, cd.getId());
        em.close();
        assertNotNull(findCD);
        assertNotNull(findCD.getId());
        assertEquals(findCD.getTitle(), "Selling England by the Pound");
    }

    @Test
    public void removeCD() {
        final CD cd = generateCD();
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
        em.close();

        cdService.removeCD(cd.getId());

        final CD findCD = emf.createEntityManager().find(CD.class, cd.getId());
        assertNull(findCD);
    }

    @Test
    public void testRemoveCD() {
        final CD cd = generateCD();
        cdService.createCD(cd);

        cdService.removeCD(cd.getId());

        assertNull(emf.createEntityManager().find(CD.class, cd.getId()));
    }

    @Test
    public void findCD() {
        final CD cd = generateCD();

        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
        em.close();

        final CD findCD = cdService.findCD(cd.getId());
        assertNotNull(findCD);
        assertEquals(findCD.getId(), cd.getId());
    }
}
