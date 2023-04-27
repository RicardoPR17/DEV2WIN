package com.dev2win.iniciativas.data.ideas;

import java.time.LocalDate;
import java.util.Objects;

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
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private Long userId;

    public Initiative(String description, String state, String keyword1, String keyword2, String keyword3, Long userId) {
        this.description = description;
        this.state = state;
        this.date = LocalDate.now();
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.userId = userId;
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
        result = prime * result + ((keyword1 == null) ? 0 : keyword1.hashCode());
        result = prime * result + ((keyword2 == null) ? 0 : keyword2.hashCode());
        result = prime * result + ((keyword3 == null) ? 0 : keyword3.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Initiative other = (Initiative) obj;
        return Objects.equals(initiativeId, other.initiativeId) &&
            Objects.equals(description, other.description) &&
            Objects.equals(date, other.date) &&
            Objects.equals(state, other.state) &&
            Objects.equals(keyword1, other.keyword1) &&
            Objects.equals(keyword2, other.keyword2) &&
            Objects.equals(keyword3, other.keyword3);
    }

    @Override
    public String toString() {
        return "Initiative [initiativeId=" + initiativeId + ", description=" + description + ", date=" + date
                + ", state=" + state + ", keyword1=" + keyword1 + ", keyword2=" + keyword2 + ", keyword3=" + keyword3
                + "]";
    }

}
