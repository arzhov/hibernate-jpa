package com.arzhov.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Musician {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "first_name", length = 50)
  protected String firstName;

  @Column(name = "last_name", length = 50)
  protected String lastName;

  @Column(length = 5000)
  protected String bio;

  @Column(name = "date_of_birth")
  @Temporal(TemporalType.DATE)
  protected Date dateOfBirth;

  @Transient
  protected Integer age;

  @Column(name = "preferred_instrument")
  private String preferredInstrument;

  @ManyToOne(fetch = FetchType.LAZY)
  private CD cd;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Musician() {
  }

  public Musician(final String firstName, final String lastName, final String bio, final Date dateOfBirth, final Integer age, final String preferredInstrument) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bio = bio;
    this.dateOfBirth = dateOfBirth;
    this.age = age;
    this.preferredInstrument = preferredInstrument;
  }

  public Musician(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(final String bio) {
    this.bio = bio;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(final Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(final Integer age) {
    this.age = age;
  }

  public String getPreferredInstrument() {
    return preferredInstrument;
  }

  public void setPreferredInstrument(final String preferredInstrument) {
    this.preferredInstrument = preferredInstrument;
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================


  @Override
  public String toString() {
    return "Musician[" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", preferredInstrument=" + preferredInstrument + ']';
  }
}