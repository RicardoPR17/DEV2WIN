package com.dev2win.iniciativas.data.comments;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.users.User;

@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "commentId")
  private Long commentId;

  @Column(name = "dateComment")
  private LocalDate date;

  @Column(name = "comment_initiative")
  private String commentary;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "initiative_id")
  private Initiative initiative;

  public Comment(String commentary, User user, Initiative initiative) {
    this.date = LocalDate.now();
    this.commentary = commentary;
    this.user = user;
    this.initiative = initiative;
  }

  public Comment() {

  }

  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getCommentary() {
    return commentary;
  }

  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Initiative getInitiative() {
    return initiative;
  }

  public void setInitiative(Initiative initiative) {
    this.initiative = initiative;
  }
}
