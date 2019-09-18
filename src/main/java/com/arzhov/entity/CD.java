package com.arzhov.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class CD extends Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(name = "total_duration")
  private Float totalDuration;

  private String genre;

  @OneToMany(
//          mappedBy = "cd",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  @JoinColumn(nullable=false)
  private Set<Musician> musicians = new HashSet<>();

  // ======================================
  // =            Constructors            =
  // ======================================

  public CD() {
  }

  public CD(final String title) {
    this.title = title;
  }

  public CD(final String title, final String genre) {
    this.title = title;
    this.genre = genre;
  }

  public CD(final String title, final String description, final Float unitCost, final Float totalDuration, final String genre) {
    this.title = title;
    this.description = description;
    this.unitCost = unitCost;
    this.totalDuration = totalDuration;
    this.genre = genre;
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

  public Float getTotalDuration() {
    return totalDuration;
  }

  public void setTotalDuration(final Float totalDuration) {
    this.totalDuration = totalDuration;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(final String genre) {
    this.genre = genre;
  }

  public Set<Musician> getMusicians() {
    return musicians;
  }

  public void setMusicians(final Set<Musician> musicians) {
    this.musicians = musicians;
  }

  public void addMusician(final Musician musician) {
    this.musicians.add(musician);
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================


  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final CD cd = (CD) o;
    return Objects.equals(totalDuration, cd.totalDuration) &&
            Objects.equals(genre, cd.genre) &&
            Objects.equals(musicians, cd.musicians);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), totalDuration, genre, musicians);
  }

  @Override
  public String toString() {
    return "CD[" + "totalDuration=" + totalDuration + ", genre=" + genre + ", musicians=" + musicians + ", " + super.toString() + ']';
  }
}