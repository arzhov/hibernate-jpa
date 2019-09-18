package com.arzhov;

import javax.persistence.EntityManager;

import com.arzhov.entity.CD;

public class CDService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private final EntityManager em;

  // ======================================
  // =            Constructors            =
  // ======================================

  public CDService(final EntityManager em) {
    this.em = em;
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  public CD createCD(final CD cd) {
    em.getTransaction().begin();
    em.persist(cd);
    em.getTransaction().commit();
    return cd;
  }

  public void removeCD(final Long id) {
    final CD cd = em.find(CD.class, id);
    if (cd != null) {
      em.getTransaction().begin();
      em.remove(cd);
      em.getTransaction().commit();
    }
  }

  public CD findCD(final Long id) {
    return em.find(CD.class, id);
  }
}