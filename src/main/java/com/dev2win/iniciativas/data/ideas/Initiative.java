package com.dev2win.iniciativas.data.ideas;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "initiatives")
public class Initiative {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long initiativeId;
  private String description;
  @Column(name = "registration_date")
  private LocalDate date;
  private String state;

  public Initiative(String description, String state) {
    this.description = description;
    this.state = state;
    this.date = LocalDate.now();
  }

  public Initiative() {

  }

  public Long getInitiativeId() {
    return initiativeId;
  }

  public void setInitiativeId(Long initiativeId) {
    this.initiativeId = initiativeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getState() {
    return state;
  }
  
  public void setState(String state) {
    this.state = state;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((initiativeId == null) ? 0 : initiativeId.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Initiative other = (Initiative) obj;
    if (initiativeId == null) {
      if (other.initiativeId != null)
        return false;
    } else if (!initiativeId.equals(other.initiativeId))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    return true;
  }
  
}
