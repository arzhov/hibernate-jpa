package com.arzhov;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.arzhov.entity.CD;

public final class Main {

  private Main() {
  }

  public static void main(final String[] args) {

    System.out.println("\n\n>>> Executing : " + Main.class + " <<<\n");

    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    final EntityManager em = emf.createEntityManager();
    final EntityTransaction tx = em.getTransaction();

    final ItemService service = new ItemService(em);

    // Creates and persists a CD
    tx.begin();
    CD cd = new CD("Sergent Pepper");
    cd = service.createCD(cd);
    tx.commit();

    System.out.println("CD Persisted : " + cd);

    // Finds the cd
    cd = service.findCD(cd.getId());

    System.out.println("CD Found     : " + cd);

    // Removes the cd
    tx.begin();
    service.removeCD(cd);
    tx.commit();

    System.out.println("CD Removed");

    // Finds the cd
    cd = service.findCD(cd.getId());

    System.out.println("CD Not Found : " + cd);

    em.close();
    emf.close();
  }
}


