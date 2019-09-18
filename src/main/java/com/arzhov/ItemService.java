package com.arzhov;

import javax.persistence.EntityManager;

import com.arzhov.entity.CD;

public class ItemService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private final EntityManager em;

  // ======================================
  // =            Constructors            =
  // ======================================

  public ItemService(final EntityManager em) {
    this.em = em;
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  public CD createCD(final CD cd) {
    em.persist(cd);
    return cd;
  }

  public void removeCD(final CD cd) {
    em.remove(em.merge(cd));
  }

  public CD findCD(final Long id) {
    return em.find(CD.class, id);
  }

}