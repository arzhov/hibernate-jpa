package com.arzhov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue
  protected Long id;

  @Column(length = 100)
  protected String title;

  @Column(length = 3000)
  protected String description;

  @Column(name = "unit_cost")
  protected Float unitCost;

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public Float getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(final Float unitCost) {
    this.unitCost = unitCost;
  }


  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Item item = (Item) o;

    if (description != null ? !description.equals(item.description) : item.description != null) return false;
    if (id != null ? !id.equals(item.id) : item.id != null) return false;
    if (!title.equals(item.title)) return false;
	  return unitCost != null ? unitCost.equals(item.unitCost) : item.unitCost == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + title.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (unitCost != null ? unitCost.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Item[" + "id=" + id + ", title=" + title + ", description=" + description + ", unitCost=" + unitCost + ']';
  }
}