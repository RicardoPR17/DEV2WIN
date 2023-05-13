package com.dev2win.iniciativas.data.likes;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.users.User;
@Entity
public class Upvote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long upvoteId;

    @ManyToOne
    @JoinColumn(name = "initiative_id")
    private Initiative initiative;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Upvote(Initiative initiative, User user) {
        this.initiative = initiative;
        this.user = user;
    }

    public Upvote() {

    }

    public Long getUpvoteId() {
        return upvoteId;
    }

    public void setUpvoteId(Long likeId) {
        this.upvoteId = likeId;
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
        Upvote upvote = (Upvote) o;
        return upvoteId.equals(upvote.upvoteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upvoteId);
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + upvoteId +
                ", initiative=" + initiative +
                ", user=" + user +
                '}';
    }
}
