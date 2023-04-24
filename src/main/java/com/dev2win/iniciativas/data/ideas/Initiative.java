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
  private String status;

  public Initiative(String description, String status) {
    this.description = description;
    this.status = status;
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

  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((initiativeId == null) ? 0 : initiativeId.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
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
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    return true;
  }
  
}
