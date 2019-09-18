package com.arzhov.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book extends Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 15)
  private String isbn;

  @Column(name = "nb_of_pages")
  private Integer nbOfPage;

  @Column(name = "publication_date")
  @Temporal(TemporalType.DATE)
  private Date publicationDate;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Book() {
  }

  public Book(final String title) {
    this.title = title;
  }

  public Book(final String title, final String isbn) {
    this.title = title;
    this.isbn = isbn;
  }

  public Book(final String title, final String description, final Float unitCost, final String isbn, final Integer nbOfPage, final Date publicationDate) {
    this.title = title;
    this.description = description;
    this.unitCost = unitCost;
    this.isbn = isbn;
    this.nbOfPage = nbOfPage;
    this.publicationDate = publicationDate;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(final String isbn) {
    this.isbn = isbn;
  }

  public Integer getNbOfPage() {
    return nbOfPage;
  }

  public void setNbOfPage(final Integer nbOfPage) {
    this.nbOfPage = nbOfPage;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(final Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final Book book = (Book) o;
    return Objects.equals(isbn, book.isbn) &&
            Objects.equals(nbOfPage, book.nbOfPage) &&
            Objects.equals(publicationDate, book.publicationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), isbn, nbOfPage, publicationDate);
  }

  @Override
  public String toString() {
    return "Book[" + "isbn=" + isbn + ", nbOfPage=" + nbOfPage + ", publicationDate=" + publicationDate + ", " + super.toString() + ']';
  }
}