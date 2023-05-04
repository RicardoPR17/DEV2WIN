package com.dev2win.iniciativas.data.likes;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.users.User;

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "initiative_id")
    private Initiative initiative;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like(Long likeId, Initiative initiative, User user) {
        this.likeId = likeId;
        this.initiative = initiative;
        this.user = user;
    }

    public Like() {

    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return likeId.equals(like.likeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likeId);
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", initiative=" + initiative +
                ", user=" + user +
                '}';
    }
}
